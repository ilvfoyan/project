//定义全局变量
var lastsel;
var item_selected = ["1","3","6"];
//var cPage=0;
var checkBox= new Array();//用来存储选中行的id
var trfa = new Array();//用来存储选中行的审批状态
var onCheck = 0;
var onSelect = 0;
//var trfat = new Array();
var Istrue = false;
var isSelectAll = false;
var SelectList = [];


$(function(){
	//页面加载完成之后执行
	pageInit();
});

//取消选中行
function deleteData(rowid){    
    for(var i = 0; i < checkBox.length; i++){    
        if (checkBox[i] == rowid){    
            //根据索引值删除checkBox中的数据    
            checkBox.splice(i, 1); 
            trfa.splice(i,1);
        }    
    }    
};


//重置
function setNull() {
    searchForm.reset();
    //清空所有值
    checkBox= [];
    trfa = [];
    onCheck = 0;
    onSelect = 0;
    Istrue = false;
    isSelectAll = false;
    SelectList = [];
};

//查询
function query() {
    $('#list2').datagrid('load', serializeObject($('#searchForm')));
}

function pageInit(){
	
	//创建jqGrid组件
	
	jQuery("#list2").jqGrid(
			{
				url : 'CheckServlet',//组件创建完成之后请求数据的url
				datatype : "json",//请求数据返回的类型。可选json,xml,txt
				
				colNames : [ '采购单号', '项目名', '物料编码', '品名规格', '采购数量','含税单价', '税率', '付款方式', 
					'供应商名称', '备注', '审批状态','订单日期', '审批人','采购人' ],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
		             {name : 'id',index : 'id',width : 120,align : "center"}, 
		             {name : 'pname',index : 'pname',width : 80,align : "center"}, 
		             {name : 'num',index : 'num',width : 160,align : "center"}, 
		             {name : 'spec',index : 'spec',width : 500,align : "center"}, 
		             {name : 'amount',index : 'amount',width : 100,align : "center"}, 
		             {name : 'price',index : 'price',width : 80,align : "center"}, 
		             {name : 'rate',index : 'rate',width : 80,align : "center"},
		             {name : 'payway',index : 'payway',width : 120,align : "center"}, 
		             {name : 'supname',index : 'supname',width : 360,align : "center"}, 
		             {name : 'remark',index : 'remark',width : 120,align : "center"}, 
		             {name : 'status',index : 'status',width : 120,align : "center"}, 
		             {name : 'date',index : 'date',width : 120,align : "center"}, 
		             {name : 'approver',index : 'approver',width : 120,align : "center"}, 
		             {name : 'purchaser',index : 'purchaser',width : 120,align : "center"} 
		           ],
				
				
				
				rownumbers:true,//添加左侧行号
				rowNum : 10,//一页显示多少条
				rowList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100],//可供用户选择一页显示多少条
				pager : '#pager2',//表格页脚的占位符(一般是div)的id
				sortname : 'id',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				mtype : "post",//向后台请求数据的ajax的类型。可选post,get
				
		        shrinkToFit: false,
	            
		        //autoScroll: false,//添加水平滚动条
		        emptyrecords:'暂无数据',//当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture 且有page时起作用
				viewrecords : true,
				//modal:true,
				caption : "审批表",//表格的标题名字
				height:'234px',
				autowidth: true, //自动宽
				multiselect:true,	//给表格添加复选框
				//multiboxonly: true, //表示只能点击复选框多选 
				/*loadComplete: function (xhr) {//添加一直存在的滚动条
	                $("#list2").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
	            },*/

	            
                //每次加载完数据或者jqGrid中的数据变动都会加载一次
                gridComplete : function(data) {
                	//设置表格标题样式
                	$('#list2').closest("div.ui-jqgrid-view")
                	               .children("div.ui-jqgrid-titlebar")
                	               .css("font-size", "24px")
                	               .children("span.ui-jqgrid-title")
                	               .css("float", "none");
                	
                    var ids = $("#list2").jqGrid('getDataIDs');
                	//console.log("加载判断id的大小：" + ids.length);
                    for (var i = 0; i < ids.length; i++) {
                    	var ch = $("#list2").jqGrid('getCell', ids[i], "status");
                    	//console.log(ch + ".");
                    	if(ch == "未审批"){
                    		//console.log(i);
                    		//$('#' + ids[i]).find("td").addClass("SelectBG");
                    		$("#"+ids[i]+ " td").css("color","#FF6347");
                    	}
                        if ((checkBox.indexOf(ids[i]) > -1)) {
                        	//console.log(checkBox[i]);
                        	//console.log("gridComplete中的ids[" + i +"]:" + ids[i]);
                            $("#list2").jqGrid('setSelection', ids[i]);//保持选中行
                        }
                    }
                	//设置表头宽度跟内容一致
                   /* $("#list2_cb").width('19px');
                    $("#list2_id").width('119px');
                    $("#list2_pname").width('79px');
                    $("#list2_num").width('159px');
                    $("#list2_spec").width('499px');
                    $("#list2_amount").width('99px');
                    $("#list2_price").width('79px');
                    $("#list2_rate").width('79px');
                    $("#list2_payway").width('119px');
                    $("#list2_supname").width('359px');
                    $("#list2_remark").width('119px');
                    $("#list2_status").width('119px');
                    $("#list2_date").width('119px');
                    $("#list2_approver").width('119px');
                    $("#list2_purchaser").width('119px');*/
                    
                    if(isSelectAll){
                    	/*console.log("开始执行！");
	                    var ids = $("#list2").jqGrid('getDataIDs');
	                    for (var i = 0; i < ids.length; i++) {
	                    	if(!(unSelects.indexOf(ids[i])>-1)){
	                            $("#list2").jqGrid('setSelection', ids[i]);
	                            var p = ($("#list2").jqGrid('getCell', ids[i], "status"));
	                            trfa.push(p);
	                        }
	                    }*/
                    }else{
                    	console.log("取消全选！");
                    	//$("list2").trigger("reloadGrid");
                    	/*var ids = $("#list2").jqGrid('getDataIDs');
	                    for (var i = 0; i < ids.length; i++) {
	                    	unSelects.splice($.inArray(rowid, unSelects), 1);
	                    	if(!(unSelects.indexOf(ids[i])>-1)){
	                            $("#list2").jqGrid('setSelection', ids[i]);
	                            var p = ($("#list2").jqGrid('getCell', ids[i], "status"));
	                            trfa.pop(p);
	                        }
	                    } */
                    }
                    
                    
                    if(isSelectAll){//当jqgrid 为全选状态数据加载完成后，将除了unSelects的其他行,全部设定为选中
                        var ids = $("#list2").jqGrid('getDataIDs');
                        for ( var i = 0; i < ids.length; i++) {
                            if(!(unSelects.indexOf(ids[i]) > -1)){
                                $("#list2").jqGrid('setSelection',ids[i]);
                            }
                            
                           /* if (checkBox.indexOf(ids[i]) == -1){
                                checkBox.push(rowid); 
                                var p = ($("#list2").jqGrid('getCell', checkBox[onSelect], "status"));
                                trfa.push(p);
                                onSelect++;   
	                        }*/
                        }
                    }
                    
                },
                
                //选择一行后获取该行id并存储
               onSelectRow:function(rowid,status){ 
            	   
            	   /*if(isSelectAll){ //当状态为全选
                       if(!status){ // 每一次取消选中时，将该行id添加到unSelects中
                           unSelects.push(rowid);
                           onCheck--;
		                    if (checkBox.indexOf(rowid)>-1){
	                            deleteData(rowid);  
		                    }
                       }else{
                           if(unSelects.indexOf(rowid)>-1){//每一次勾选时，检查该行是否在unSelects中，
                               //若在，则在unSelects中删除该记录
                               //（翻页也会触发onselect事件，所以需检查该行是否在unselects中）
                                   unSelects.splice($.inArray(rowid, unSelects), 1)
                               }
                           if (checkBox.indexOf(rowid) == -1){
	                            checkBox.push(rowid); 
	                            var p = ($("#list2").jqGrid('getCell', checkBox[onCheck], "status"));
	                            trfa.push(p);
	                            onCheck++;
	                        }   
                           }
            	   	  }else{
            	   */
		            	  /*if(status){  
		                    	//console.log("选中的行id：" + rowid);
		                      if (checkBox.indexOf(rowid) == -1){
		                            checkBox.push(rowid); 
		                            var p = ($("#list2").jqGrid('getCell', checkBox[onCheck], "status"));
		                            trfa.push(p);
		                            onCheck++;
		                        }    
		                    }else{
		                    	onCheck--;
			                    if (checkBox.indexOf(rowid)>-1){
		                            deleteData(rowid);  
			                    }
		                    }    */
            	   	  //}
		            	  
		            	 
		            	  
		            	  console.log(status);
		            	   if (status) { // 每一次取消选中时，将该行id添加到SelectList中
		            		   
		            	        if (SelectList.indexOf(rowid) == -1) {//每一次勾选时，检查该行是否在SelectList中，
		            	            //若在，则在SelectList中删除该记录
		            	            //（翻页也会触发onselect事件，所以需检查该行是否在unselects中）
		            	        	console.log(rowid);
		            	            SelectList.push(rowid);
		            	            checkBox.push(rowid);
		            	            console.log(SelectList[onCheck]);
		            	            var p = ($("#list2").jqGrid('getCell', SelectList[onCheck], "status"));
		                            trfa.push(p);
		                            console.log(onCheck);
		                            console.log(trfa[onCheck]);
		                            onCheck++;
		            	        }
		            	    } else {
		            	    	onCheck--;
		            	        if (SelectList.indexOf(rowid) > -1) {//每一次勾选时，检查该行是否在SelectList中，
		            	            //若在，则在SelectList中删除该记录
		            	            //（翻页也会触发onselect事件，所以需检查该行是否在unselects中）
		            	            SelectList.splice($.inArray(rowid, SelectList), 1);
		            	            deleteData(rowid);
		            	            console.log("取消选中行！");
		            	        }
		            	    }
		            	   console.log("SelectList.length" + SelectList.length);
		            	   console.log("checkBox.length" + checkBox.length);
		            	   console.log("trfa.length" + trfa.length);
                  },
                  
            
            
            
                  //点击全选.和点击一行类似，只不过现在选的行id变成了行id数组。
                  onSelectAll:function(rowids,status){
                	  
                	  if (status) {
                		    console.log("全选时数据个数：" + rowids.length);
                	        for (var i = 0; i < rowids.length; i++) {//获取当前页的数据           
                	            if (SelectList.indexOf(rowids[i]) == -1) {//每一次勾选时，检查该行是否在SelectList中，
                	                //若在，则在SelectList中删除该记录
                	                //（翻页也会触发onselect事件，所以需检查该行是否在unselects中）
                	                SelectList.push(rowids[i]);
                	                console.log("SelectList[" + i + "]:" + SelectList[i])
                	                var p = ($("#list2").jqGrid('getCell', SelectList[onCheck], "status"));
		                            trfa.push(p);
                	            }
                	        }
                	    } else {
                	        for (var i = 0; i < rowids.length; i++) {
                	            if (SelectList.indexOf(rowids[i]) > -1) {//每一次勾选时，检查该行是否在SelectList中，
                	                //若在，则在SelectList中删除该记录
                	                //（翻页也会触发onselect事件，所以需检查该行是否在unselects中）
                	                SelectList.splice($.inArray(rowids[i], SelectList), 1);
                	                trfa.splice($.inArray(rowids, trfa), 1);
                	            }
                	        }
                	    }
                	  
                	  
                	  console.log("全选的状态：" + status);
                	  isSelectAll = status;
                	  unSelects = [];  //每次点击全选时，无论勾选还是取消,都将没有被选中的行清空
                	  /*if(!isSelectAll){
                		  checkBox = [];
                		  trfa = [];
                	  }else{
                		  
                	  }*/
		          },
		});
	
		//让表格宽度随浏览器窗口大小变化而自适应宽度
		$(function(){
		   $(window).resize(function(){   
		      $("#list2").setGridWidth($(window).width()-159);
		   });
		}); 
	
	
	
		 
	
		jQuery("#bt").click(function() {
		    //var ids = jQuery("#list2").jqGrid('getGridParam','selarrrow');//获取当前页的多个选中行的id，其输出格式是逗号分隔的id(也只能获取当前页的选中行)
		    //var celldata = $("#list2").jqGrid('getCell',ids,0);
		    //var length = checkBox.length;//保存选中行的个数
			var length = SelectList.length;
		    //console.log(checkBox.length);
			console.log(SelectList.length);
		    if (length <= 0) {
                alert("请选择你要审批的订单！");
                return;
            } else {
	            if (window.confirm("确认要审批选中的订单?")) {
	                var celldata = "";
	                //console.log("选中订单数：" + length);
	                for(var i = 0; i < length; i++) {
                    	//判断所选的全部订单中是否含有已审批的订单
                        var IsDeliver = false;
                        for (var j = 0; j < length; j++) {
                        	console.log(trfa[j] + ".");
                            if(trfa[j] == "已审批"){
                                IsDeliver = true;
                                break;
                            }
                        }
                        if(IsDeliver) {
                            alert("检测到所选的订单中有订单已审批，请重新选择！");
                            return;
                        }else{
                        	var username = $("#username").val();//获取当前登录的用户名
		                    //console.log(username);
		                    //将正确的选中行进行修改数据，将“未审批”状态改成“已审批”状态,并存储审批人的用户名信息
	                    	var sentData = {"ds":SelectList[i],"username":username};
	                    	//console.log("username:" + username);
	                        $.ajax({
	                            url:"CheckedServlet", //访问路径
	                            type:"POST",    //访问方式
	                            data:sentData, //传入服务端的数据
	                            /*data:{//发送给servlet的数据
	                                name:ids
	                                },*/
	                            dataType:"json",
	                            
	                            success :function(){
	                               //添加成功代码
	                            }
	                        });
		                }//判断是否已审批的else结束

	                }//外for结束
		            checkBox = [];//清空选中行
		            alert("审批成功！");
		            
		            jQuery("#list2").trigger("reloadGrid");//对当前页进行重载刷新
	            
	            }//确定要审批的if结束
            }//外else结束
	  });//button结束
	

	  //查询功能
		jQuery("#querylink").click(function() {
			var searchIdValue = encodeURIComponent($("#id").val());
			var searchNumValue = encodeURIComponent($("#num").val());
			var searchDateValue = encodeURIComponent($("#date").val());
			searchDateValue = searchDateValue.replace("-", "");
			searchDateValue = searchDateValue.replace("-", "");
			var searchChooseValue = $("#choose").val();
			console.log(searchIdValue + "," + searchNumValue + "," + searchDateValue + "," + searchChooseValue + ".");
			var postJson = {"id":searchIdValue,"num":searchNumValue,"date":searchDateValue,"choose":searchChooseValue};
			
			//传入查询条件参数
			$("#list2").jqGrid("setGridParam",{postData:postJson}); 
			//每次提出新的查询都转到第一页
			$("#list2").jqGrid("setGridParam",{page:1});
			//提交post并刷新表格
			$("#list2").jqGrid("setGridParam",{url:'SearchServlet'}).trigger("reloadGrid")
		});
		
		
		//重置功能
		jQuery("#reSet").click(function(){
			setNull();
			jQuery("#list2").trigger("reloadGrid");//对当前页进行重载刷新
		});
		
	
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	jQuery("#list2").jqGrid('navGrid', '#pager2', {edit : false,add : false,del : false,search : false,refresh : false,view : false});
}