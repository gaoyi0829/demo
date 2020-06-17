<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>账单列表(管理员版)</title>
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="layui/layui.js"></script>
	<script src="js/Application.js"></script>
	<script src="js/json2.js"></script>

</head>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px; margin-bottom: 50px">
    <legend style="font-size: 40px">账单列表(管理员版)</legend>
</fieldset>

<div class="searchAllOrders" >
按客户证件号查询：
<form class="layui-form" action="" lay-filter="searchOrders">
	  <div class="layui-inline">
    <input class="layui-input"  name="searchOCondition" id="searchOCondition" autocomplete="off" lay-filter="searchOCondition">
  </div>
  <button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">查询</button>
</form>
</div>


<table class="layui-hide" id="ordersTable" lay-filter="ordersTable"></table>
<script type="text/html" id="operation">
<a class="layui-btn  layui-btn-xs" style="background-color: gold ;color: black" lay-event="check">查看信息</a>
</script>
<!--  <a class="layui-btn  layui-btn-xs" style="background-color: gold ;color: black" lay-event="check">查看信息</a>    每一行的按钮  在上个script运行  没用到所以拿出来进行注释-->
<script>
	layui.use(['form', 'layer', 'table','laydate'], function(){  //如果只加载一个模块，可以不填数组。如：layui.use('form')
		var table = layui.table;	
		var layer = layui.layer;
		var form  = layui.form;
		var laydate = layui.laydate;
	
	    $=layui.jquery;

	    
		  //自定义表单验证 			//-----------------------------------------------------------------------------------自定义表单验证
		    form.verify({
	
		           customerid:[
		        	   /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
		               , '证件号不正确！'
		           ],
		           customername:[
		        	   /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/
		        	   ,'啊？这。  请输入正确的姓名吧'
		           ],
		           customerphone:[
		        	   /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
		        	   , '手机号不正确！'
		           ]
			    
	         });
		/*  
	    //入住日期选择
					lay('#in_date').on('click', function(e){ //假设 test1 是一个按钮
				  laydate.render({
				    elem: '#in_date'
				    ,show: true //直接显示
				    ,closeStop: '#in_date' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
				  });
				});
		//退房日期选择
		
					lay('#out_date').on('click', function(e){ //假设 test1 是一个按钮
				  laydate.render({
				    elem: '#out_date'
				    ,show: true //直接显示
				    ,closeStop: '#out_date' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
				  });
				});
		  **/
	  //监听提交按钮
	  form.on('submit(SformSub)', function(text){
		  var searchForm=form.val("searchOrders") //form.val(xxx) xxx:layfilter的值
	  
			  table.reload('ordersReload', {
			        page: {
			       //   curr: 1 //重新从第 1 页开始
			      	curr:$(".layui-laypage-em").next().html()
			        }
			        ,where: {
			          pageName:1,    //默认page
		            limitName:1,    //默认limit
			          condition:searchForm.searchOCondition
			        },
			        
			      }, 'data');
		$('#searchCondition').val('');
		  return false;
			
		});
	  
	  //实例化一个控件
	        table.render({
            elem: '#ordersTable' //table的id
            ,url:'ordersList'  //数据接口    ordersList
            ,page: true //开启分页
            ,limit:10
            ,limits:[10,15,20]
            ,toolbar:'default'    //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,cols: [[//表头	
            	  {type: 'checkbox', fixed: 'left'}
                ,{field:'order_id', width:100, title: '订单号',sort: true}
                ,{field:'order_state', width:100, title: '订单状态',sort: false}
                ,{field:'room_id', width:100, title: '房间号',sort: false}
                ,{field:'in_date', width:150, title: '入住时间',sort: false} 	
                ,{field:'out_date', width:150, title: '退房时间',sort: false}
                ,{field:'customer_id', width:170, title: '身份证号',sort: false}
                ,{field:'customer_name', width:150, title: '姓名',sort: false}
                ,{field:'customer_phone', width:150, title: '手机号',sort: false}
                ,{field:'room_type', width:140, title: '房间类型',sort: false}
                ,{field:'price', width:100, title: '账单费用',sort: false}

            ]]
      	    ,where: {condition:''}
    	    ,id:'ordersReload'
     	   	,request:{
            	pageName:'ordersPage',    //默认page
            	limitName:'ordersSize'    //默认limit
            }

        });
	  
	        //监听员工列表头工具栏事件	//-----------------------------------------------------------------------------------监听账单列表  头工具栏事件
	        table.on('toolbar(ordersTable)', function(obj){
	          var form  = layui.form;
	          var checkStatus = table.checkStatus(obj.config.id)
	          ,data = checkStatus.data //获取选中的数据
              ,orderIds=[]
 	          ,orderStates=[]
	          ,roomIds=[]
	          ,inDates=[]
	          ,outDates=[]
 	          ,customerIds=[] 
	          ,customerNames=[] 
	          ,customerPhones=[] 
	          ,roomTypes=[]
	          ,prices=[];
	          //------------------------------------------------------------------------------------------------------获取被选中项的各项信息的list
	          data.forEach(function(n,i){
	        	  orderIds.push(n.order_id);
				  
	          });
	          data.forEach(function(n,i){
	        	  orderStates.push(n.order_state);
				  
	          });
	          data.forEach(function(n,i){
	        	  roomIds.push(n.room_id);
				  
	          });
	          data.forEach(function(n,i){
	        	  inDates.push(n.in_date);
				  
	          });
	          data.forEach(function(n,i){
	        	  outDates.push(n.out_date);
				  
	          });
	          data.forEach(function(n,i){
	        	  customerIds.push(n.customer_id);
				  
	          });
	          data.forEach(function(n,i){
	        	  customerNames.push(n.customer_name);
				  
	          });
	          data.forEach(function(n,i){
	        	  customerPhones.push(n.customer_phone);
				  
	          });
	          data.forEach(function(n,i){
	        	  roomTypes.push(n.room_type);
				  
	          });
	          data.forEach(function(n,i){
	        	  prices.push(n.price);
				  
	          });
//	          console.log(customerIds)
//			  console.log(typeof customerIds)
			 
			  
	          var ayon1=-1, //add yes or no
	          dyon1=-1;   //del yes or no 
	          switch(obj.event){
	            case 'add':					//-----------------------------------------------------------------------------------头工具点击添加
	                  var index=layer.open({//-----------------------------------------------------------------------------------弹出添加员工弹出层
	            		  closeBtn: 1,
	            	      type: 1,
	            	      area: ['500px', '720px'],
	            	      shadeClose: true, //点击遮罩关闭
	            	      content: $('#addOrds').html(),
	            	      success: function(layero, index){
	            	    	  }
	            	      })
	            	      	  
	   					 //常规用法
	  					  laydate.render({
	  					    elem: '#in_date'
	 					   });
	                    //常规用法
  					       laydate.render({
  					         elem: '#out_date'
 						   });
	            	      form.render();   //弹出层加载的content是静态代码，加载后需再渲染一遍form
	            	      form.on('submit(addformsub)', function(formData){
	            	    	  console.log("fromData==========="+formData);
	            	    	   var odsInf=form.val("addOrdsTable")       //获取表单信息   
	              		 	 	console.log("name====="+odsInf.in_date);
	              		 	 	console.log("name====="+odsInf.out_date);
	            	    	 
	                  		   $.post({
	                  		    	url:'addOrder',
	                  		     //   method : 'post',
	                  		    	data:{
	                  		    		
	                  		    		'orderState':odsInf.order_state,
	                  		    		'roomId':odsInf.room_id,
	                  		    		'inDate':odsInf.in_date,
	                  		    		'outDate':odsInf.out_date,
	                  		    		'customerId':odsInf.customer_id,
	                  		    		'customerName':odsInf.customer_name,
	                  		    		'customerPhone':odsInf.customer_phone,
	                  		    		'roomType':odsInf.room_type,
	                  		    		'price':odsInf.price
	                  		    	},
	                  		 	 	 success:function(ayon){
	                  		 	 		ayon1=ayon;
	                  		 	 	 	
	                  		 	console.log("ayon1======="+ayon1)
	                  		 	console.log("ayon========"+ayon)
	                  		 	 	if(ayon1==1){
	                        			  layer.msg('添加成功！');
	                          		  layer.close(index); //关闭
	                          		  //window.location.reload();// 刷新页面
	                        			}else if(ayon1==0){
	                        			  layer.msg('添加失败，用户号已存在，请重新输入')
	                        			}else{
	                        				layer.msg("error");
	                        			}
	                   				     // var yon1=yon;
	                   		  }
	                  		    })	
	                  		    
	                  		    console.log("data1==========="+ayon1);
	                  		   
	                  		 table.reload('ordersReload', {
							        page: {
							        	curr:$(".layui-laypage-em").next().html()
							        	//curr:praseTable.config.page.curr
							        	}
							        ,where: {
							          pageName:1,    //默认page
						              limitName:10,    //默认limit
							          condition:''
							        },
							         
							      }, 'data');
								
	                  		    return false;
	                  		  });
	            	      
	            break;
	            case 'update':		//-----------------------------------------------------------------------------------头工具点击修改
	              if(data.length === 0){
	                layer.msg('请选择一行');
	              } else if(data.length > 1){
	                layer.msg('只能同时编辑一个');
	              } else {
	            				  //-----------------------------------------------------------------------------------弹出修改信息弹出层
	          	  layerIndex=layer.open({																	
	          		  closeBtn: 1,
	          	      type: 1,
	          	      area: ['500px', '750px'],
	          	      shadeClose: true, //点击遮罩关闭
	          	      content: $('#updateOrds').html(),
	          	      
	          	      success: function(layero, index){

						//常规用法
	  					  laydate.render({
	  					    elem: '#u_in_date'
	 					   });
	                    //常规用法
					       laydate.render({
					         elem: '#u_out_date'
						   });

                     
	          	   	 form.render();
	          	   	 
	          	   	 //打开修改弹出框自动填入账单原本的信息
	               	form.val("updOdsTable", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
	               	  "order_id": orderIds // "name": "value"
	               	  ,"order_state": orderStates
	               	  ,"room_id": roomIds
	               	  ,"in_date": inDates
	               	  ,"out_date": outDates
	                  ,"customer_id": customerIds
	               	  ,"customer_name": customerNames
	               	  ,"customer_phone": customerPhones
	               	  ,"room_type": roomTypes
	                  ,"price": prices
	                  

	               	});
	          	    }
	          	
	            });
	          	 form.on('submit(updateformsub)', function(text){               //修改信息弹出框的提交按钮监听
	          		 var updInf=form.val("updOdsTable")       //获取表单信息

	         		   $.post({
	         		    	url:'updateOrder',
	         		    	data:{
              		    		'orderId':updInf.order_id,
              		    		'orderState':updInf.order_state,
              		    		'roomId':updInf.room_id,
              		    		'inDate':updInf.in_date,
              		    		'outDate':updInf.out_date,
              		    		'customerId':updInf.customer_id,
              		    		'customerName':updInf.customer_name,
              		    		'customerPhone':updInf.customer_phone,
              		    		'roomType':updInf.room_type,
              		    		'price':updInf.price
	         		    		//'txt':encodeURI(document.getElementById("txt").value)
	         		   			// 'txt':JSON.stringify(text.field)
	         		    	},
	         		 	 	 success:function(data){
	          				  console.log(data);
	          		  }
	         		    })	
	         		  
	         		    //layer.close(layerIndex); //再执行关闭
	         		     window.location.reload();// 刷新页面
	         		    layer.msg('修改成功！');
	         		    return false;
	         		  });
	              }
	            break;
	            case 'delete':			//-----------------------------------------------------------------------------------头工具点击删除
	            	 console.log(orderIds)
	   			  console.log(typeof orderIds)  
	            if(data.length === 0){
	                layer.msg('请选择一行');
	              } else {
	                layer.msg('删除');
	                if(orderIds!=''){
	                	layer.confirm('确定删除所选项吗？',function(index){
	                		$.post({
	                			url:'delOrders',
	                			data:
	                				"orderIds="+orderIds,
	                			success:function(dyon){
	                				dyon1=dyon;
	              		 	 		console.log("dyon=========="+typeof dyon);
	              		 	 		console.log("dyon1=========="+typeof dyon1);
	              		 	 		console.log("dyon=========="+dyon);
	              		 	 		console.log("dyon1=========="+dyon1);
	              		 	 	if(dyon1==1){
									layer.msg('删除成功！');
									window.location.reload();// 刷新页面
	                    			}else if(dyon1==0){
	                    			  layer.msg('删除失败')
	                    			}else{
	                    				layer.msg("error");
	                    			}
	                			}
	                		
	                		})
	                		//console.log(orderIds)
        		
	                	})
	                }else{
	                	layer.msg('请选择一行');
	                }

	              }
	            break;
	          };
	        });

	     
	      
	      
	      
	  
	  });
	
	
	
	
</script>
<!-- Detail3Table -->

<script type="text/html" id="addOrds">

	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
	   		 <legend style="font-size: 20px">添加账单</legend>
		</fieldset>

	<form class="layui-form" action="" lay-filter="addOrdsTable">

	  <div class="layui-form-item">
	    <label class="layui-form-label">账单号</label>
	    <div class="layui-input-block">
	      <input type="text" id="order_id" name="order_id" required  lay-verify="required|orderid" placeholder="请输入订单号" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_order_id">
	    </div>
	  </div>

	   <div class="layui-form-item">
	    <label class="layui-form-label">订单状态</label>
	    <div class="layui-input-block" style="width: 300px">   
	      <select id="order_state" name="order_state" lay-verify="required"  lay-filter="a_order_state">
	        <option value=""></option>
			  <option value="预订">预订</option>
			  <option value="进行中">进行中</option>
			  <option value="已完成">已完成</option>
			  <option value="退订">退订</option>
	      </select>
	    </div>
	  </div>

	  <div class="layui-form-item">
	    <label class="layui-form-label">房间号</label>
	    <div class="layui-input-block">
	      <input type="text" id="room_id" name="room_id" required  lay-verify="required" placeholder="请输入房间号" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_room_id">
	    </div>
	  </div>


  
    <div class="layui-form-item">
      <label class="layui-form-label">入住时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" name="in_date" id="in_date" placeholder="" style="width: 300px" lay-filter="a_in_date">
      </div>
    </div>

    

	    <div class="layui-form-item">
      <label class="layui-form-label">退房时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" name="out_date" id="out_date" placeholder="" style="width: 300px" lay-filter="out_date">
      </div>
    </div>

	
	<div class="layui-form-item">
	    <label class="layui-form-label">顾客证件号</label>
	    <div class="layui-input-block">
	      <input type="text" id="customer_id" name="customer_id" required  lay-verify="required|customerid" placeholder="请输入顾客证件号" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_customer_id">
	    </div>
	  </div>
	
	<div class="layui-form-item">
	    <label class="layui-form-label">顾客姓名</label>
	    <div class="layui-input-block">
	      <input type="text" id="customer_name" name="customer_name" required  lay-verify="required|customername" placeholder="请输入顾客姓名" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="customer_name">
	    </div>
	  </div>

	<div class="layui-form-item">
	    <label class="layui-form-label">顾客手机号</label>
	    <div class="layui-input-block">
	      <input type="text" id="customer_phone" name="customer_phone" required  lay-verify="required|customerphone" placeholder="请输入顾客手机号" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_customer_phone">
	    </div>
	  </div>

	<div class="layui-form-item">
	    <label class="layui-form-label">房间类型</label>
	    <div class="layui-input-block" style="width: 300px">
	      <select id="room_type" name="room_type" required lay-verify="required">
	        <option value=""></option>
	        <option value="主题景观双床房">主题景观双床房</option>
	        <option value="主题景观大床房">主题景观大床房</option>
	        <option value="主题景观家庭房">主题景观家庭房</option>
	        <option value="主题景观豪华房">主题景观豪华房</option>
	        <option value="商务景观家庭房">商务景观家庭房</option>
	      </select>
        </div>
	  </div>

	<div class="layui-form-item">
	    <label class="layui-form-label">金额</label>
	    <div class="layui-input-block">
	      <input type="text" id="price" name="price" required  lay-verify="required" placeholder="请输入金额" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_price">
	    </div>
	  </div>

	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="addformsub">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
	</script>
	

	
	
	<script type="text/html" id="updateOrds">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
	   		 <legend style="font-size: 20px">修改订单信息</legend>
             <legend style="font-size: 12px">注意：不能更改账单号</legend>
		</fieldset>
	<form class="layui-form" action="" lay-filter="updOdsTable">
	  <div class="layui-form-item">
	    <label class="layui-form-label">账单号</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_order_id" name="order_id" required  lay-verify="required" placeholder="账单号不可修改" autocomplete="off" class="layui-input" disabled="" style="width: 300px">
	    </div>
	  </div>

	   <div class="layui-form-item">
	    <label class="layui-form-label">订单状态</label>
	    <div class="layui-input-block" style="width: 300px">   
	      <select id="u_order_state" name="order_state" required lay-verify="required">
	        <option value=""></option>
			  <option value="预订">预订</option>
			  <option value="进行中">进行中</option>
			  <option value="已完成">已完成</option>
			  <option value="退订">退订</option>
	      </select>
	    </div>
	  </div>

	  <div class="layui-form-item">
	    <label class="layui-form-label">房间号</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_room_id" name="room_id" required  lay-verify="required" placeholder="请输入房间号" autocomplete="off" class="layui-input" style="width: 300px">
	    </div>
	  </div>


  
    <div class="layui-form-item">
      <label class="layui-form-label">入住时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="u_in_date"  name="in_date" placeholder="" style="width: 300px" lay-filter="a_in_date">
      </div>
    </div>

    

	    <div class="layui-form-item">
      <label class="layui-form-label">退房时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="u_out_date" name="out_date" placeholder="" style="width: 300px" lay-filter="a_out_date">
      </div>
    </div>


	
	<div class="layui-form-item">
	    <label class="layui-form-label">顾客证件号</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_customer_id" name="customer_id" required  lay-verify="required|customerid" placeholder="请输入顾客证件号" autocomplete="off" class="layui-input" style="width: 300px" >
	    </div>
	  </div>
	
	<div class="layui-form-item">
	    <label class="layui-form-label">顾客姓名</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_customer_name" name="customer_name" required  lay-verify="required|customername" placeholder="请输入顾客姓名" autocomplete="off" class="layui-input" style="width: 300px">
	    </div>
	  </div>

	<div class="layui-form-item">
	    <label class="layui-form-label">顾客手机号</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_customer_phone" name="customer_phone" required  lay-verify="required|customerphone" placeholder="请输入顾客手机号" autocomplete="off" class="layui-input" style="width: 300px">
	    </div>
	  </div>

	<div class="layui-form-item">
	    <label class="layui-form-label">房间类型</label>
	    <div class="layui-input-block" style="width: 300px">
	      <select id="u_room_type" name="room_type" required lay-verify="required">
	        <option value="主题景观双床房">主题景观双床房</option>
	        <option value="主题景观大床房">主题景观大床房</option>
	        <option value="主题景观家庭房">主题景观家庭房</option>
	        <option value="主题景观豪华房">主题景观豪华房</option>
	        <option value="商务景观家庭房">商务景观家庭房</option>
	      </select>
        </div>
	  </div>


	 <div class="layui-form-item">
	    <label class="layui-form-label">金额</label>
	    <div class="layui-input-block">
	      <input type="text" id="u_price" name="price" required  lay-verify="required" placeholder="请输入金额" autocomplete="off" class="layui-input" style="width: 300px">
	    </div>
	  </div>

	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="updateformsub">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>

</script>	
</body>
</html>