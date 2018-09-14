define(['placeholder','modalbutton','autocomplete','../util/core.js','../jquery-qrcode/jquery.qrcode.min.js',
    './tpl/signup.js',
    './tpl/signin.js',
    './tpl/erweima.js',
    './tpl/phoneVerity.js',
    './tpl/registerFinished.js',
    './tpl/emailVerity.js',
    './tpl/email_RegisterFinished.js',
    './tpl/phoneValidate.js',
    '../backbone/backbone-1.0.0.min.js'], function() {

 Loginview = Backbone.View.extend({

        initialize: function(options) {
            this.options = options;
            if(options&&options.mode=="signup"){
                this.dom = signupTpl;
            }else{
                this.dom = signinTpl;
            }
            this.interval = null;
            this.val = null;
            this.loginWithCode = false;
            this.verifyLoad = false;
            this.IfPWDTypeChange = true;
            var _this = this;
            moco.validateCallback['checkusername'] = function(value){

               _this.blurToCheckUserName(value);
            };
            moco.validateCallback['checkverity'] = function(value){
               _this.checkverity(value);
            }
        },

        events: {
            "mousedown #signup-btn":    "clickToSignup",
            "click .js-verify-refresh":   "clickToRefreshVerifyCode",
            "click .xa-showSignup":   "clickToShowSignup",
            "click .xa-showSignin":   "clickToShowSignin",
            "mousedown .js-proclaim":   "clickToProclaimCode",
            "click .xa-showQrcode":   "clickToShowQrcode",
            "click .xa-hideQrcode":   "clickToHideQrcode",
            "click .xa-refresh":   "clickToShowQrcode",
            "keyup .xa-emailOrPhone":   "keyupCheckEmailOrPhone",
            "focus .xa-emailOrPhone":   "focusautocomplete",
            "focus input":   "focusToHideError",
            "blur .xa-emailOrPhone":   "blurToTrim",
            "click .js-reSend":   "clickToresendPhoneCode",
            "click .js-voicecode-send":   "clickToresendVoiceCode",
            "click .js-back":   "clickToBack",
            "click [data-login-sns]":   "clickShowotherLogin",
            "click .xa-endRegister":   "clickToFinishedRegiter",
            "mousedown .xa-submitePhoneVerity":    "clickTosubmitePhoneVerity",
            "click .xa-login":    "clickToLogin",
            "keyup .js-loginPassword":    "keyupJudgeIfShwoVerity",
            "keyup .js-loginWrap input":    "keyupToTriggerLogin",
            "keyup .js-registerWrap input":    "keyupToTriggerRegister",
            "keyup .js-phoneVerityWrap input":    "keyupToTriggerSubmitePhoneVerity",
            "click #signup-protocol":    "clickProtocol",
            "click #js-gotoVerity":    "clickToVerityEmail",
        },  
        setCookie:function(name, value, options)
        {
            // name and value given, set cookie
            if (typeof value != 'undefined')
            {
                options = options || {};
                if (value === null)
                {
                    value = '';
                    options.expires = -1;
                }
                var expires = '';
                if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString))
                {
                    var date;
                    if (typeof options.expires == 'number') {
                        date = new Date();
                        date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                    } else {
                        date = options.expires;
                    }
                    expires = '; expires=' + date.toUTCString();
                }
                var path   = options.path ? '; path=' + (options.path) : '';
                var domain = options.domain ? '; domain=' + (options.domain) : '';
                var secure = options.secure ? '; secure' : '';
                document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
            }
        },
        keyupToTriggerLogin:function(e){
            if(e.keyCode=="13"){
                this.$el.find(".xa-login").trigger('click');
            }
        },

        keyupToTriggerRegister:function(e){
            if(e.keyCode=="13"){
                this.$el.find("#signup-btn").trigger('mousedown');
            }

        },

        keyupToTriggerSubmitePhoneVerity: function(e){
            if(e.keyCode=="13"){
                this.$el.find(".xa-submitePhoneVerity").trigger('mousedown');
            }
        },

        keyupJudgeIfShwoVerity: function(){
            if(!this.loginWithCode){
                if(!this.verifyLoad){
                        this.verifyLoad = true;
                        var _this = this;
                        $.get( imoocSSO.checkVerifyUrl,"username="+$('[name="email"]').val(), function(data){
                                if(data.status == 10001){
                                _this.showLoginVerify();
                                }
                            },"json");
                        }
                }else{
                    this.showLoginVerify();
                 }
                                

        },
        clickProtocol:function(){
            if (!$("#signup-protocol")[0].checked) {
                $("#signup-protocol").parents(".rlf-group").find('.rlf-tip-wrap').addClass("rlf-tip-error").html("请同意慕课网注册协议");
            }else{
                $("#signup-protocol").parents(".rlf-group").find('.rlf-tip-wrap').removeClass("rlf-tip-error").html("");
            }
        },
        showLoginVerify:function(){
            // alert('showLoginVerify')
            // alert( this.$el.find('.js-verify-row').css("display"));
            
            if( this.$el.find('.js-verify-row').css("display")=='none') {

                this.$el.find('.js-verify-row').show();
                this.$el.find('.verify-img-wrap').append(
                    $('<img class="verify-img"/>')
                );
            }
            this.refreshVerifyCode();
        },

        focusToHideError:function(){
            $('.rlf-tip-globle').text('');
        },

        blurToTrim:function(event){
             $(event.currentTarget).val($.trim( $(event.currentTarget).val()));
        },

        focusautocomplete: function(event){
            $(event.currentTarget).autocomplete();
        },

        clickToSignin: function(){
        },

        clickShowotherLogin: function(event){
            this.winsns.open($(event.currentTarget).attr("data-login-sns"));
        },

        keyupCheckEmailOrPhone: function(event){
            var $node = $(event.currentTarget);
            if($node.val().indexOf("@")!=-1) {
                $node.attr("data-validate","require-email");
                //$node.parent().find(".errorHint").attr("data-error-hint","邮箱格式错误");
                this.$el.find(".xa-passwordWrap").show();
            }else{
                $node.attr("data-validate","require-mobile-phone");
                  //$node.parent().find(".errorHint").attr("data-error-hint","手机号格式错误");
                this.$el.find(".xa-passwordWrap").hide();
            }
           
        },
         math:function(){
            var genericEmailLinks="sohu.com::http://mail.sohu.com \
                        |sina.com,sina.cn :: http://mail.sina.com \
                        |vip.sina.com :: http://vip.sina.com.cn \
                        |126.com :: http://www.126.com \
                        |163.com :: http://mail.163.com \
                        |vip.163.com :: http://vip.163.com \
                        |vip.126.com :: http://vip.126.com \
                        |qq.com,vip.qq.com :: http://mail.qq.com \
                        |msn.com,outlook.com,hotmail.com,live.cn,live.com :: http://outlook.com \
                        |gmail.com :: http://www.gmail.com \
                        |yahoo.com.cn,yahoo.cn,aliyun.com :: http://mail.aliyun.com \
                        |yahoo.com.tw :: http://mail.yahoo.com.tw \
                        |21cn.com :: http://mail.21cn.com \
                        |tom.com :: http://mail.tom.com/ ",
            genericEmailLinksMap={};

            $.each(genericEmailLinks.split("|"),function(index,v){
                var val=v.split("::"),
                    v=$.trim(val[1]),
                    i,len;
                val=val[0].split(",");
                for(i=0,len=val.length;i<len;i++){
                    genericEmailLinksMap[$.trim(val[i])]=v;
                }
            });


            var link = genericEmailLinksMap[this.val.username.match(/[^@]*$/)[0]];
            return link;


        },
        blurToCheckUserName: function(value){
            var username = value;
            moco.validateCallback.rel = false;
            var url="/passport/user/checkphone"
            ,data= {phone:username};
            if(username.indexOf("@")!=-1){
              
                url=imoocSSO.checkUserName;
                data = {username:username};
            }
            $.ajax({
                url:url,
                method:"get",
                async: false,
                data:data,
                dataType:"json",
                success:function(data){
                    if(data.status==10001){
                        moco.validateCallback['errorHint'] = '';
                        moco.validateCallback.rel = true;
                    }
                    else{
                        moco.validateCallback['errorHint'] = data.msg;
                        moco.validateCallback.rel = false;
                        
                       // $(event.currentTarget).parent().find(".errorHint").html("fail");
                        
                    }
                },
                error:function(){
                    moco.validateCallback['errorHint'] = "网络错误"
                   
                },
            }) 

        },
        clickToVerityEmail: function(){
            var link = this.math()
            window.open(link);
            //window.location.reload();

        },
        checkverity: function(value){
            moco.validateCallback.rel = false;
            $.ajax({
                url:imoocSSO.checkVerifyCode,
                method:"get",
                async: false,
                data:{verify: value},
                dataType:"json",
                success:function(data){
                    if(data.status == 10001){
                        moco.validateCallback['errorHint'] = '';
                        moco.validateCallback.rel = true;
                    }
                    else{
                        moco.validateCallback['errorHint'] = data.msg;
                    }
                },
                error:function(){
                    moco.validateCallback['errorHint'] = "网络错误"
                   
                },
            })
        },

        clickToSignup: function(event){
          
            if (!W.validate(this.$el.find(".xa-emailOrPhone").parent())) {
                return;
            }
            if (!W.validate(this.$el.find(".ipt-verify ").parent())) {
                return;
            }
            if (!$("#signup-protocol")[0].checked) {
                $("#signup-protocol").parents(".rlf-group").find('.rlf-tip-wrap').addClass("rlf-tip-error").html("请同意慕课网注册协议");
                return;
            }

            $(event.currentTarget).text("正在注册...");
             $(event.currentTarget).attr("disabled","disabled");

            var data = {
                username: this.$el.find("[name='email']").val(),
                password: this.$el.find("[name='password']").val(),
                verify: this.$el.find(".ipt-verify").val(),
            }

            this.val = data;

            if(data.username.indexOf("@")!=-1) {
                this.emailRegister(data);
            }else{

                this.phoneRegister(data);
            }
        },

        clickToShowSignup: function(){
            clearInterval(this.interval);
            this.dom = signupTpl;
            this.render();
        },

        clickToShowSignin: function(){
            clearInterval(this.interval);
            this.dom = signinTpl;
            this.render();

        },

        clickToShowQrcode: function(){
            clearInterval(this.interval);
            this.dom = erweimaTpl;
            this.render();
            var code = new GUID().newGUID();
            //这个是生成二维码用的，必须是完整路径
            var codeStr =  "https://www.imooc.com?ma="+code;
            $('#qrcode').qrcode({width: 150,height: 150,text: codeStr});
            this.loopScan(code);
        },

        clickToHideQrcode: function() {
            clearInterval(this.interval);
            this.dom = signinTpl;
            this.render();
        },

        clickToProclaimCode: function(event) {
            event.stopPropagation();
            this.proclaimCode();
        },

        clickToRefreshVerifyCode: function(){
            this.refreshVerifyCode();
        } ,

        clickTosubmitePhoneVerity: function(vals) {
          
            if (!W.validate(this.$el.find("#js-phoneVerity").parent())) {
                return;
            }
            
            if (!W.validate(this.$el.find(".js-pass-pwd ").parent())) {
                return;
            }
            $(".xa-submitePhoneVerity").text("正在提交...");
            $(".xa-submitePhoneVerity").attr("disabled","disabled"); 
            var _this = this;
            var plantform = $.getUrlParam('plantform');
            var val ={
                number:$(".js-phoneNumber").html(),
                mobileverify:$("#js-phoneVerity").val(),
                password:$("#js-password").val(),
                type:1,
                referer:window.location.protocol+"//"+window.location.hostname,
                plantform: plantform,
            }
            var _data = { username:$(".js-phoneNumber").html(),}
            $.ajax({
                url:"/passport/user/phoneregister",
                data:val,
                method:"post",
                dataType:"json",
                success:function(res){
                    if(res.status == 10001){

                            // var uid = res['userInfo']['uid'];
                            // var ipaddata = {};
                            // ipaddata.account = val.number;
                            //  ipaddata.uid = uid;
                            // ipaddata.plantform = $.getUrlParam('plantform');
                            // alert(JSON.stringify(ipaddata));
                            // if(plantform=='ipad'){
                            //     window.webkit.messageHandlers.registerSuccess.postMessage(JSON.stringify(ipaddata));
                            //     return;
                            // }

                            imoocSSO.crossDomainAction(function(){
                                _this.showRegisterFinished(_data);
                                
                            })
                            imoocSSO.setCrossDomainCookie(res['data']['url']);

                    }else{
                        $("#signin-globle-error").addClass("rlf-tip-error").html(res['msg']);
                    }
                    
                },
                error:function(res){
                   
                    $("#signup-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
                },
                complete:function(){

                    $(".xa-submitePhoneVerity").text("提交").removeAttr("disabled").removeClass("disabled");
                }

            })

        },


        clickToFinishedRegiter: function(){
            window.location.href="//www.imooc.com/user/setprofile"
        },

        showPhoneVerity: function(val){
            this.dom = phoneVerityTpl;
            this.render();
            $(".js-phoneNumber").html(val.username);
            var appendVoicecodeFlag = 0;
            var index = 60;
                clearInterval(this.interval);
                this.interval = setInterval(function(){
                $(".js-second").parent().removeClass("js-reSend");
                $(".js-second").parent().removeClass("active");
                $(".js-second").html(index+'s 后');
                if(index<1){
                    $(".js-second").parent().addClass("active");
                    $(".js-second").parent().addClass("js-reSend");
                    $(".js-second").parent().html("短信验证");
                    $(".js-second").html("");
                    if(!appendVoicecodeFlag) {
                        // 增加语音验证码
                        var html = '<p class="voicecode-send js-voicecode-send"><i class="imv2-vol_up"></i>语音接听</p>';
                        $(".js-reSend").after(html);
                        appendVoicecodeFlag = 1;
                    }
                    
                    clearInterval(this.interval);
                }
                index--;
            },1000)
        },

        clickToBack: function() {
            this.clickToShowSignup();
        },


        clickToShowSignin: function(){
            this.dom = signinTpl;
            this.render();
        },
        showEmailRegisterFinished: function(data){
            this.dom = email_RegisterFinishedTpl;
            this.render();
            $(".js-account").html(data.username);

            if($(".js-account").width()>164){
                $(".js-account").wrapInner("<div class='breakall'></div>")
            }

        },
        showRegisterFinished: function(data){
            this.dom = registerFinishedTpl;
            this.render();
            $(".js-account").html(data.username);

            if($(".js-account").width()>164){
                $(".js-account").wrapInner("<div class='breakall'></div>")
            }
            var index = 5;
            var interval=setInterval(function(){
                $(".xa-endRegister").val("完成["+index+"]");
                if(index<1){
                    clearInterval(interval);
                    window.location.href="//www.imooc.com/user/setprofile";
                };
                index--;
            },1000);
        },

        clickToresendPhoneCode: function() {
            clearInterval(this.interval);

            this.phoneRegister(this.val,0);
        },
        //语音验证码
        clickToresendVoiceCode: function() {
            clearInterval(this.interval);

            this.phoneRegister(this.val,1);
        },

        phoneRegister: function(vals,type) {
            $("#signup-btn").attr("disabled","disabled");
            $(".reSend").attr("disabled","disabled");
           
            var _this = this;
            var val = {
                number:vals.username,
                verify: vals.verify
            };
            if(type) {
                val.typecode = 1;
            }
            $.ajax({
                url:"/passport/user/phoneregister",
                data:val,
                method:"post",
                dataType:"json",
                success:function(data){
                     if(data.status == 10001){

                        _this.showPhoneVerity(vals);

                        // $(".rl-modal-body").html(getTpl("phoneVerity"));

                        
                     }else{
                        
                        $("#signup-btn").text("注册").removeAttr("disabled").removeClass("disabled");
                        $("#signup-globle-error").addClass("rlf-tip-error").html(data.msg);
                        //重发5次 不允许再发
                        if(data.status == 11001){
                            $('.rlf-tip-globle').text(data.msg);
                            $(".js-reSend").css("color","#B4B8BB");
                            $(".js-reSend").css("cursor","default");
                            $(".js-reSend").removeClass("active");
                            $(".js-reSend").html("");
                            $(".js-voicecode-send").html("");
                            $(".js-reSend").removeClass("js-reSend");
                            $(".js-second").html("");
                        }
                    }

                },
                error:function(){
                    $("#signup-btn").text("注册").removeAttr("disabled").removeClass("disabled");
                    $("#signup-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
                },
                complete:function(){
                    $("#signup-btn").text("注册").removeAttr("disabled").removeClass("disabled");
                    $(".reSend").removeAttr("disabled");
                }

            })


        },

        emailRegister: function(vals) {
            var _this = this;
            $("#signup-btn").attr("disabled","disabled");
            var plantform = $.getUrlParam('plantform');
            if(plantform == 'ipad'){
                vals.plantform = plantform;
            }
            imoocSSO.register({
                data:vals,
                success:function(data){
                    //data.data={userInfo:""}
                    if(data.status==10001){
                        var uid = data.data['userInfo']['uid'];
                        var ipaddata = {};
                        ipaddata.account = vals.username;
                        ipaddata.uid =uid.toString();
                        ipaddata.plantform = $.getUrlParam('plantform');
                        if(ipaddata.plantform=='ipad'){
                             window.webkit.messageHandlers.registerSuccess.postMessage(JSON.stringify(ipaddata));
                        }

                        var index = 5;
                        _this.showEmailRegisterFinished(vals);
                    }else if(data.status == 10024){
                        _this.setCookie('urlcookie', document.URL, { expires : 1, domain:'imooc.com', path:"/"});
                        _this.showEmailRegisterFinished(vals);
                    }

                    $("#signup-globle-error").addClass("rlf-tip-error").html(data.msg);

                    // 重置验证码

                },
                error:function(){
                    $("#signup-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
                },
                complete:function(){
                    $("#signup-btn").text("注册").removeAttr("disabled").removeClass("disabled");
                }
            })
        },

        loopScan: function(code){
            var _this = this;
            var index = 0;
            var _code = code;
            function scanCode(){
                
                if(!$(document).find(".js-pageLogin").length){
                    
                    if(!$(document).find(".modal-backdrop").length){
                        return
                    }
                }
                if(index>=30){
                    clearInterval(_this.interval);
                    $(".qrcode-bk-validate").removeClass("hide");
                    $(".qrcode-bk-scand").addClass("hide");
                }else{
                    $.ajax({
                        url:"/passport/user/ScanCodesz",
                        method:"post",
                        data:{codeid: _code},
                        dataType:"json",
                        success:function(obj){
                            //var obj = JSON.parse(data); 
                            var status = obj.data[0]['status']
                            if( status != 3) {
                                if(status == 0){
                                    $(".qrcode-bk-scand").removeClass("hide");
                                    $(".qrcode-bk-validate").addClass("hide");
                                }
                                else{
                                    if(status == 1) {
                                        clearInterval(_this.interval);
                                        _this.getToken({
                                            keyid: obj.data[0]['keyid'],
                                            uid: obj.data[0]['uid'],
                                            codeid: _code,
                                         });
                                        ///passport/user/scancode
                                     // {"result":0,"data":[{"status":"0","kyeid":"8a461c0046ffc97fa75df66372c9a51f"}],"msg":1000}
                                    }
                                    if(status == 2) {
                                        $(".qrcode-bk-scand").addClass("hide");
                                        $(".qrcode-bk-validate").addClass("hide");
                                    }
                                }
                            }

                        },
                        error:function(data){
                        }
                    })
                }
                index++;
            }
            clearInterval(this.interval);
            this.interval= setInterval(scanCode, 2000);

        },

        clickToLogin: function(event) {
            $('.xa-emailOrPhone').keyup();
            if (!W.validate(this.$el.find(".xa-emailOrPhone").parent())) {
                return;
            }
            if (!W.validate(this.$el.find(".js-pass-pwd ").parent())) {
                return;
            }

            if (!W.validate(this.$el.find(".ipt-verify ").parent())) {
                return;
            }

            var data = {
                username: this.$el.find("[name='email']").val(),
                password: this.$el.find("[name='password']").val(),
                verify: this.$el.find(".ipt-verify").val(),
            }
            this.val = data;


            var _this = this;
            $(".xa-login").val("正在登录...");
            $(".xa-login").attr("disabled","disabled");        
            var $this=$(event.currentTarget);
            var signInForm=$('#signup-form');
            if($this.hasClass("disabled")){ return;}
            var remember=$("#auto-signin")[0].checked?"1":"0",
                    params = {
                        username:data.username,
                        password:data.password,
                        verify:data.verify,
                        remember:remember,
                        pwencode:1,
                        browser_key:OP_CONFIG.browser_key
                    };

                //sso方式登录
                imoocSSO.login({
                    data:params,
                    success:function(data){
                        data.data={userInfo:""}
                        if(data.status==10001){
                            _this.fireLogined(data.data.userInfo);
                            return ;
                        }

                        else if(data.status==900001){
                            window.location.href="//www.imooc.com/user/userfrozen";
                            return ;
                        }
                        else if(data.status == 10005||data.status == 10007||data.status == 90003){ // 需要出验证码
                            _this.showLoginVerify();
                        }
                        else if(data.status == 10024){
                            _this.setCookie('urlcookie', document.URL, { expires : 1, domain:'imooc.com', path:"/"});
                            window.location.href = "//www.imooc.com/user/checkemail";
                            return;
                        }
                        $("#signin-globle-error").addClass("rlf-tip-error").html(data.msg);

                        if(_this.loginWithCode){
                            _this.refreshVerifyCode();
                            signInForm.find('.ipt-verify').val('');
                        }
                        //$("#signin-btn").button("reset");
                    },
                    error:function(){
                        $("#signin-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
                    },
                    complete:function(){
                         $(".xa-login").val("登录").removeAttr("disabled").removeClass("disabled");
                    }
                })
                    

        },

        fireLogined: function(data,signup) {
            var e=$.extend($.Event("logined.imooc"),{_data:data});
            $("#signin [data-dismiss],#signup [data-dismiss]").trigger("click");
            $(document).trigger(e);
            this.winsns.clear();

            if(e.isDefaultPrevented()){
                return ;
            }
            //default actions; can be prevent by call e.preventDefault method
            if(signup) {
                window.location.replace("//www.imooc.com/user/setprofile");
                return ;
            }
            var pathname=window.location.pathname,forward;

            forward="error,forget,logout,newforgot,userfrozen,sendresult,resetpasspage,resetpassword,checkaopenguser".split(",").join("|");
            forward=new RegExp("\\/(?:"+forward+")(?:\\/|$|\\?|#)");
            if(forward.test(pathname)){ //remove pathname=="/" index login
                window.location.replace("/course/list"); ///index
                return ;
            }
            if(OP_CONFIG&&OP_CONFIG.page=='newlogin'){
                if(fromURL!=undefined){
                    window.location.replace(fromURL);
                    return ;
                }
            } 

            window.location.reload(); 
        },

        getToken : function (ops){
            $.ajax({
                url:"/passport/user/scancode",
                method:"post",
                dataType:"json",
                data:ops,
                success:function(res){
                    
                    if( res.data.caution){
                        
                        $('#signin').hide()
                        $('.modal-backdrop').hide()
                        
                        var str = '<p class="warn-info" style="font-size: 16px;line-height: 28px;max-width: 600px;">'+res.data.caution+'</p>';
                            str += '<div class="moco-modal-btns">';
                                str += '<a class="moco-btn moco-btn-blue js-modal-close" href="javascript:void(0)">'
                                    str += '<span>确定</span>';
                                str += '</a>';
                            str += '</div>';
                        
                        if( $.dialog){
                            $.dialog( str,{
                                title: '警告',
                                modal: true,
                                callback: function(){
                                    window.location.href = '//www.imooc.com/index/usercheck?uid=' + res.data.userInfo.uid;
                                }
                            });
                        }else{
                            alert(res.data.caution)
                            window.location.href = '//www.imooc.com/index/usercheck?uid=' + res.data.userInfo.uid;
                        }
                        return;
                    }
                    
                    imoocSSO.crossDomainAction(function(){
                        window.location.reload();
                    })
                    imoocSSO.setCrossDomainCookie(res['data']['url'])
                },
                error:function(error){
                }
            })
        },

        proclaimCode : function(){
            if(this.IfPWDTypeChange){
                if($(".js-pass-pwd").attr("type")=="password"){
                    $(".js-pass-pwd").attr("type",'text');
                }else{
                    $(".js-pass-pwd").attr("type",'password');
                }
                this.IfPWDTypeChange = false;
                var _this = this;
                setTimeout(function(){
                    _this.IfPWDTypeChange = true;
                },200)
            }
        },

        refreshVerifyCode: function(){
            this.$el.find('.verify-img').attr('src', imoocSSO.verifyCodeUrl+"?t=" + new Date().getTime()); 
        } ,

        // showLoginVerify: function(){
        //     this.loginWithCode = true;
        //     this.$el.find('.js-verify-row').show();
        // },

     

        winsns: function(){
            var o={};

            function clearPrev(){//dereference
                for(var key in o){
                    if(key.indexOf("/user")>-1){
                        o[key].close&&o[key].close();
                        o[key]=null;
                        delete o[key];
                    }
                }
            }

            return {
                open:function(url){
                    var l,t;
                    if(o[url]&&o[url].closed===false){
                        o[url].focus&&o[url].focus();
                        return ;
                    }
                    clearPrev();
                    l=(screen.width-650)/2,
                    t=(screen.height-400)/2;
                    (o[url]=window.open(url+"&browser_key="+OP_CONFIG.browser_key+"&referer="+window.location.protocol+"//"+window.location.hostname, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=650, height=500, top='+t+', left='+l)).focus();
                },
                clear:clearPrev
            }
        }(),

        render: function(mode) {
            if(mode == 'signin'){
                this.dom = signinTpl;
                clearInterval(this.interval);
            }
            if(mode == 'signup'){
                this.dom = signupTpl;
                console.log('===========================')
                clearInterval(this.interval);
            }
            this.loginWithCode=false;
            this.verifyLoad = false
            $(".rl-modal").remove();
            $(".modal-backdrop").remove();
            this.$el.append(this.dom);
            
            if(this.dom!==signinTpl) {
                this.$el.find('.verify-img-wrap').append(
                    $('<img class="verify-img"/>')
                );
                this.refreshVerifyCode();
            }else{
                this.$el.find('.js-verify-row').hide();
            }
            var m=$(".rl-modal");
            m.modallogin("show");
            if(typeof(ownName) !== "undefined"){
                $(".js-own-name").val(ownName)
            };
            var $node =$(".xa-emailOrPhone");
            if($node.val()){
                if($node.val().indexOf("@")!=-1) {
                    $node.attr("data-validate","require-email");
                }else{
                    $node.attr("data-validate","require-mobile-phone");
                }
            }
        }

    });


    var winsns=(function(){
        var o={};

        function clearPrev(){//dereference
            for(var key in o){
                if(key.indexOf("/user")>-1){
                    o[key].close&&o[key].close();
                    o[key]=null;
                    delete o[key];
                }
            }
        }

        return {
            open:function(url){
                var l,t;
                if(o[url]&&o[url].closed===false){
                    o[url].focus&&o[url].focus();
                    return ;
                }
                clearPrev();
                l=(screen.width-650)/2,
                t=(screen.height-400)/2;
                (o[url]=window.open(url+"&referer="+window.location.protocol+"//"+window.location.hostname, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=650, height=500, top='+t+', left='+l)).focus();
            },
            clear:clearPrev
        }
    })();



    var fireLogined=window.__fireLogined=window.__fireLogined||function(data,signup){

        var e=$.extend($.Event("logined.imooc"),{_data:data});
        $("#signin [data-dismiss],#signup [data-dismiss]").trigger("click");
        $(document).trigger(e);
        winsns.clear();

        if(e.isDefaultPrevented()){
            return ;
        }
        //default actions; can be prevent by call e.preventDefault method
        if(signup) {
            window.location.replace("//www.imooc.com/user/setprofile");
            return ;
        }
        var pathname=window.location.pathname,forward;

        forward="error,forget,logout,newforgot,userfrozen,sendresult,resetpasspage,resetpassword,checkaopenguser,newsignup".split(",").join("|");
        forward=new RegExp("\\/(?:"+forward+")(?:\\/|$|\\?|#)");
        if(forward.test(pathname)){ //remove pathname=="/" index login
            window.location.replace("/course/list"); ///index
            return ;
        }
        window.location.reload();

    }

 

});
