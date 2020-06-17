<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>评价列表</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/layui/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/Application.js"></script>
    <script src="${pageContext.request.contextPath}/js/json2.js"></script>
</head>
<script type="text/javascript">
var customer_id = getCookie("cuntomer_id");
</script>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">


<fieldset class="layui-elem-field layui-field-title">
    <legend style="font-size: 40px">评价</legend>
</fieldset>
<div class="searchApp2" >
按房间类型查询：
<form class="layui-form" action="" lay-filter="searchAppraise">
	  <div class="layui-inline">
	      <select id="searchACondition" name="searchACondition" div class="layui-input" required   style="width: 300px"   autocomplete="off" lay-filter="searchACondition">
	        <option value=""></option>
	        <option value="主题景观双床房">主题景观双床房</option>
	        <option value="主题景观大床房">主题景观大床房</option>
	        <option value="主题景观家庭房">主题景观家庭房</option>
	        <option value="主题景观豪华房">主题景观豪华房</option>
	        <option value="商务景观家庭房">商务景观家庭房</option>
	      </select>
	    </div>
  <button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">查询</button>
  </form>
</div>

<script src="layui/layui.js"></script>
<table class="layui-hide" id="appsTable" lay-filter="appsTable"></table>

<script>
	layui.use(['form','layer','rate','table','laydate'],function(){  //如果只加载一个模块，可以不填数组。如：layui.use('form')
		var table = layui.table;	
		var layer = layui.layer;
		var form  = layui.form;
		var rate  = layui.rate;
		var laydate  = layui.laydate;
	    $ = layui.jquery;

	  //监听提交按钮
	  form.on('submit(SformSub)', function(text){
		  var searchForm=form.val("searchAppraise") //form.val(xxx) xxx:layfilter的值
	  
			  table.reload('appsReload',{
			        page: {
			       //   curr: 1 //重新从第 1 页开始
			      	curr:$(".layui-laypage-em").next().html()
			        }
			        ,where: {
			          pageName:1,    //默认page
		            limitName:1,    //默认limit
			          condition:searchForm.searchACondition
			        },
			 
			      }, 'data');
		$('#searchACondition').val('');
		  return false;
			
		});
	  
	  //实例化一个控件
	        table.render({
            elem: '#appsTable' //table的id
            ,url:'appsList2'  //数据接口
            ,method:'post'
            ,page: true //开启分页
            ,toolbar:'default'
            ,limit:10
            ,limits:[10]
            ,cols: [[//表头	
            
                 {field:'appraise_id', title: '编号',sort: false,align : 'center'}
                 ,{field:'appraise_star', title: '星级评价',sort: true,align : 'center'}
                 ,{field:'room_type', title: '房间类型',sort: false,align : 'center'}
                ,{field:'customer_name', title: '客户昵称',sort: false,align : 'center'}
                ,{field:'appraise_content', title: ' 评价内容',sort: false}
                ,{field:'appraise_date', title: '评价时间',sort: true,align : 'center'}
                ,{field:'appraise_reply', title: '回复',sort: false}
                ,{field:'reply_date', title: '回复时间',sort: false,align : 'center'}
                
         
            ]]
      	    ,where: {condition:''}
    	    ,id:'appsReload'
     	   	,request:{
            	 pageName:'appsPage',    //默认page
            	limitName:'appsSize'     
            
            }
	  });
	 //监听员工列表头工具栏事件	//-----------------------------------------------------------------------------------监听员工列表  头工具栏事件
    table.on('toolbar(appsTable)', function(obj){
      var form  = layui.form;
      var appStar=0;
      var checkStatus = table.checkStatus(obj.config.id)
      ,data = checkStatus.data //获取选中的数据
      //传递被选中id的list
      ,appId=[]
      ,cusId=[]
      ,roomType=[]
      ,cusName=[]
      ,appContent=[]
      ,appDate=[]
      ,appReply=[]
      ,replyDate=[]
      ;
      //------------------------------------------------------------------------------------------------------获取被选中项的各项信息的list
      data.forEach(function(n,i){
    	  appId.push(n.appraise_id);
		  
      });
    
      data.forEach(function(n,i){
    	  cusId.push(n.customer_id);
		  
      });
    
      data.forEach(function(n,i){
    	  roomType.push(n.room_type);
		  
      });
      data.forEach(function(n,i){
    	  cusName.push(n.customer_name);
		  
      });
     
      data.forEach(function(n,i){
    	  appContent.push(n.appraise_content);
		  
      });
      data.forEach(function(n,i){
    	  appDate.push(n.appraise_date);
		  
      });
      data.forEach(function(n,i){
    	  appReply.push(n.appraise_reply);
		  
      });
      data.forEach(function(n,i){
    	  replyDate.push(n.reply_date);
		  
      });
      console.log(cusId)
	  console.log(typeof cusId)
      var ayon1=-1,
      dyon1=-1;
      switch(obj.event){
        case 'add':					//-----------------------------------------------------------------------------------头工具点击添加
    
        /* 	$("#u_customer_id").value = (getCookie("customer_id")); */
        	  var index=layer.open({//-----------------------------------------------------------------------------------弹出添加员工弹出层
        		  closeBtn: 1,
        	      type: 1,
        	      area: ['500px', '500px'],
        	      shadeClose: true, //点击遮罩关闭
        	      content: $('#addApps').html(),
        	      success: function(layero, index){
        	    	  //document.getElementById("#u_customer_id").value=cookie;
        	    	  }
        	      })
        	       laydate.render({
            elem: '#u_appraise_date',
            type:'datetime',
            value: new Date(),
        });

        	  	
        	  
        	  
        	  rate.render({
        		  elem: '#u_appraise_star'
        		  ,setText: function(value){
        		    var arrs = {
        		      '1': '极差'
        		      ,'2': '差'
        		      ,'3': '中等'
        		      ,'4': '好'
        		    };
        		    this.span.text(arrs[value] || ( value + "星"));
        		  }
        		});
        	  rate.render({
        		  elem: '#u_appraise_star'
        		  ,choose: function(value){
        		    appStar=value;
        		  }
        		});
        	      form.render();   //弹出层加载的content是静态代码，加载后需再渲染一遍form
        	      form.on('submit(addformsub)', function(formData){
        	    	 // console.log("111111"+formData);
        	    	 
        	    	 var appsInf=form.val("addAppsTable")       //获取表单信息   
   		    		
          		 	 	console.log("star="+appStar);
          		 	 	console.log("content="+appsInf.appraise_content);
              		   $.post({
              		    	url:'addAppraise',
              		    	data:{
             		    		/*'empId':document.getElementById("employee_id").value,
              		    		'empName':document.getElementById("employee_name").value,
              		    		'empGender':document.getElementById("employee_gender").value,
              		    		'empAge':document.getElementById("employee_age").value,
              		    		'empPosition':document.getElementById("employee_position").value*/
              		    		
              		    		'cusId':getCookie("cusid"),
              		    		'appStar':appStar,
              		    		'roomType':appsInf.room_type,
              		    		'cusName':getCookie("cusname"),
              		    		'appContent':appsInf.appraise_content,
              		    		'appDate':appsInf.appraise_date,
              		    		
              		    		
              		    	},
              		 	 	 success:function(ayon){
              		 	 		ayon1=ayon;
              		 	 		/*console.log("ayon=========="+typeof ayon);
              		 	 		console.log("ayon1=========="+typeof ayon1);
              		 	 		console.log("ayon=========="+ayon);
              		 	 		console.log("ayon1=========="+ayon1);*/
              		 	
              		 	 	if(ayon1==1){
                    			  layer.msg('添加成功！');
                      		  layer.close(index); //关闭
                      		  window.location.reload();// 刷新页面
                    			}else if(ayon1==0){
                    			  layer.msg('添加失败，员工号已存在，请重新输入')
                    			}else{
                    				layer.msg("error");
                    			}
               				 // var yon1=yon;
               		  }
              		    })	
              		    
              		    console.log("data1==========="+ayon1);
              		   
              			

              		    return false;
              		  });
        break;
        case 'update':		//-----------------------------------------------------------------------------------头工具点击修改
        if(data.length === 0){
        layer.msg('权限不足');
      } else {
        layer.msg('权限不足');
          }
        break;
        case 'delete':			//-----------------------------------------------------------------------------------头工具点击删除
          if(data.length === 0){
            layer.msg('权限不足');
          } else {
            layer.msg('权限不足');
          
          }
        break;
      };
    });
	  });
</script>

<script type="text/html" id="addApps">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px; margin-bottom: 10px">
   		 <legend style="font-size: 20px">添加评价</legend>
	</fieldset>
<form class="layui-form" action="" lay-filter="addAppsTable">

  
<div class="layui-form-item" lay-filter="appStar">
<label class="layui-form-label">评价星级</label>
	<div id="u_appraise_star" name="appraise_star"> 
	</div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">房间类型</label>
    <div class="layui-input-block" style="width: 350px">
      <select name="room_type" lay-verify="required" style="width: 100%" >
        <option value=""></option>
        <option value="主题景观双床房">主题景观双床房</option>
        <option value="主题景观大床房">主题景观大床房</option>
        <option value="主题景观家庭房">主题景观家庭房</option>
        <option value="主题景观豪华房">主题景观豪华房</option>
        <option value="商务景观家庭房">商务景观家庭房</option>
      </select>
    </div>
  </div>
 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">评价内容</label>
    <div class="layui-input-block">
      <textarea id="u_appraise_content" name="appraise_content" placeholder="请输入内容" class="layui-textarea" style="width: 350px"></textarea>
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">评价时间</label>
    <div class="layui-input-block">
      <input type="text" id="u_appraise_date" style="width: 350px" name="appraise_date" required lay-verify="required|appDate placeholder="" autocomplete="off" class="layui-input" disabled="" style="width: 300px">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="addformsub">立即提交</button>
      
    </div>
  </div>
</form>

</script>



</body>
</html>