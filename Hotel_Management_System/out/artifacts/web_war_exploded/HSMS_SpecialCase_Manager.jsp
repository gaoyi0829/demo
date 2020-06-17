<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>特殊事件管理（管理员版）</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="layui/layui.js"></script>
</head>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px; margin-bottom: 50px">
    <legend style="font-size: 40px">特殊事件管理（管理员版）</legend>
</fieldset>
<div class="searchSpecial" >
  按事件描述搜索：
<form class="layui-form" action="" lay-filter="searchSCForm">
  <div class="layui-inline">
    <input class="layui-input"  name="searchSCCondition" id="searchSCCondition" autocomplete="off" lay-filter="searchSCCondition">
  </div>
  <button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">搜索</button>
</form>
</div>
<table class="layui-hide" id="specialTable" lay-filter="specialTable"></table>

<script type="text/html" id="operation2">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table','layer','form'], function(){
        var table = layui.table;
		var layer = layui.layer;
		var form  = layui.form;
	    $ = layui.jquery;
		  //自定义表单验证 			//-----------------------------------------------------------------------------------自定义表单验证
	    form.verify({
	    	   id:[
	        	   /^[1-9]\d{0,4}$/
	               , '事件号格式不正确！'
	           ],
	           caseInf: [
	        	   // /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{1,60}$/
	        	   // /([\s\S]*){0,60}$/
	        	   /^.{0,60}$/ 
	               , '事件描述不正确（最多60字）！'
	           ],
	           date:[
	        	   // /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{1,15}$/
	        	   // /([\s\S]*){0,15}$/
	        	   /^.{0,15}$/ 
	        	   ,'时间格式不正确(最多15个字)！'
	           ]
	          
	          
	    
	         });
		  
	 	   form.on('submit(SformSub)', function(text){
	  		  var searchForm=form.val("searchSCForm") 
	 		   // layer.msg("searchForm.searchSCondition:"+searchForm.searchSCondition)
	 		  /* $.post({
	 		    	url:'searchEmp',
	 		    	data:{
	 		    		//'num':data.employee_id,
	 		    		//'txt':document.getElementById("suggestiontxt").value
	 		    		'condition':searchForm.searchECondition
	 		    	},
	 		 	 	 success:function(data){
	  				  console.log(data);
	  		  }
	 		    })	*/
	 			 table.reload('SpecialReload', {
	 			        page: {
	 			       	curr:$(".layui-laypage-em").next().html()
	 			          //curr: 1 //重新从第 1 页开始
	 			        }
	 			        ,where: {
	 			          pageName:1,    //默认page
	 		              limitName:1,    //默认limit
	 			          condition:searchForm.searchSCCondition
	 			        },
	 			        
	 			      }, 'data');
	  		$('#searchSCCondition').val('');
	 		    return false;
	 		  });
	 	   
	    //渲染库存列表				//-----------------------------------------------------------------------------------渲染库存列表
        table.render({
            elem: '#specialTable' //table的id
            ,url:'specialList'  //数据接口
            ,page: true //开启分页
            ,limit:10
            ,limits:[10]
        	,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
         	,where:{
         		condition:''
         	}
            ,cols: [[//表头		
            	 {type: 'checkbox', fixed: 'left'}
                ,{field:'case_id', width:100, title: '事件号', sort: true}
                ,{field:'case_inf', width:1100, title: '事件描述'}
                ,{field:'case_date', width:220, title: '时间',sort:true}
            ]]
        	,id:'SpecialReload'
        	,request:{
            	pageName:'specialPage',    //默认page
            	limitName:'specialSize'    //默认limit
            }

        });
        
      
      //监听员工列表头工具栏事件	//-----------------------------------------------------------------------------------监听库存列表  头工具栏事件
        table.on('toolbar(specialTable)', function(obj){
          var form  = layui.form;
          var checkStatus = table.checkStatus(obj.config.id)
          ,data = checkStatus.data //获取选中的数据
          ,ids=[] //传递被选中id的list
          ,inf=[]
          ,date=[]
         
    
          //------------------------------------------------------------------------------------------------------获取被选中项的各项信息的list
          data.forEach(function(n,i){
              ids.push(n.case_id);
			  
          });
          data.forEach(function(n,i){
              inf.push(n.case_inf);
			  
          });
          data.forEach(function(n,i){
              date.push(n.case_date);
			  
          });
        
//---
          //console.log(ids)
		 // console.log(typeof ids)
          var ayon1=-1,
          dyon1=-1;
          switch(obj.event){
            case 'add':					//-----------------------------------------------------------------------------------头工具点击添加
            	  var index=layer.open({//-----------------------------------------------------------------------------------弹出添加员工弹出层
            		  closeBtn: 1,
            	      type: 1,
            	      area: ['500px', '500px'],
            	      shadeClose: true, //点击遮罩关闭
            	      content: $('#addSpecial').html(),    
            	      success: function(layero, index){
            	    	  }
            	      })
            	      form.render();   //弹出层加载的content是静态代码，加载后需再渲染一遍form
            	      form.on('submit(addformsub)', function(formData){ 
            	    	 // console.log("111111"+formData);
            	    	 var speInf=form.val("addSpecialForm")        //获取表单信息    
            	    	 	
              		 	 	console.log(speInf.case_id);
                  		   $.post({
                  		    	url:'addSpecial',
                  		    	data:{
                 		    		/*'empId':document.getElementById("employee_id").value,
                  		    		'empName':document.getElementById("employee_name").value,
                  		    		'empGender':document.getElementById("employee_gender").value,
                  		    		'empAge':document.getElementById("employee_age").value,
                  		    		'empPosition':document.getElementById("employee_position").value*/
                  		    		'caseId':speInf.case_id,
                  		    		'caseInf':speInf.case_inf,
                  		    		'caseDate':speInf.case_date,
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
                        			  layer.msg('添加失败，库存号已存在，请重新输入')
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
                layer.msg('请选择一行');
              } else if(data.length > 1){
                layer.msg('只能同时编辑一个');
              } else {
            				  //-----------------------------------------------------------------------------------弹出修改信息弹出层
          	  layerIndex=layer.open({																	
          		  closeBtn: 1,
          	      type: 1,
          	      area: ['500px', '500px'],
          	      shadeClose: true, //点击遮罩关闭
          	      content: $('#updateSpecial').html(),
          	      success: function(layero, index){
          	   	 form.render();
          	   	 //打开修改弹出框自动填入员工原本的信息
               	form.val("updSpecialForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
               	  "case_id": ids // "name": "value"
               	  ,"case_inf": inf
               	  ,"case_date": date
               	 
               	});
          	    }
          	
            });
          	 form.on('submit(updateformsub)', function(text){               //修改信息弹出框的提交按钮监听
          		 var updInf=form.val("updSpecialForm")       //获取表单信息
         		   $.post({
         		    	url:'updateSpecial',
         		    	data:{
         		    		'caseId':updInf.case_id,
          		    		'caseInf':updInf.case_inf,
          		    		'caseDate':updInf.case_date,
       
         		    		
         		    		//'txt':encodeURI(document.getElementById("txt").value)
         		   			// 'txt':JSON.stringify(text.field)
         		    	},
         		 	 	 success:function(data){
          				  console.log(data);
          		  }
         		    })	
         		    layer.msg('修改成功！');
         		    //layer.close(layerIndex); //再执行关闭
         		     window.location.reload();// 刷新页面
         		    return false;
         		  });
              }
            break;
            case 'delete':			//-----------------------------------------------------------------------------------头工具点击删除
              if(data.length === 0){
                layer.msg('请选择一行');
              } else {
                layer.msg('删除');
                if(ids!=''){
                	layer.confirm('确定删除所选项吗？',function(index){
                		$.post({
                			url:'delSpecial',
                			data:
                				"ids="+ids,
                			success:function(dyon){
                				dyon1=dyon;
              		 	 		console.log("dyon=========="+typeof dyon);
              		 	 		console.log("dyon1=========="+typeof dyon1);
              		 	 		console.log("dyon=========="+dyon);
              		 	 		console.log("dyon1=========="+dyon1);
              		 	 	if(dyon1==1){
							
								window.location.reload();// 刷新页面
								layer.msg('删除成功！');
                    			}else if(dyon1==0){
                    			  layer.msg('删除失败')
                    			}else{
                    				layer.msg("error");
                    			}
                			}
                		
                		})
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

<script type="text/html" id="addSpecial">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">记录特殊事件</legend>
	</fieldset>
<form class="layui-form" action="" lay-filter="addSpecialForm">
  <div class="layui-form-item">
    <label class="layui-form-label">事件号</label>
    <div class="layui-input-block">
      <input type="text" id="case_id" name="case_id" required  lay-verify="required|id" placeholder="请输入事件号（最多4位数字且不能以0开头）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_id">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">事件描述</label>
    <div class="layui-input-block">
      <textarea type="text" id="case_inf" name="case_inf" required  lay-verify="required|caseInf" placeholder="请描述事件（最多60个字！）" autocomplete="off" class="layui-input" style="width: 300px;height:160px" lay-filter="a_inf"></textarea>
    </div>
  </div>
   <div class="layui-form-item">
 <div class="layui-form-item">
    <label class="layui-form-label">时间</label>
    <div class="layui-input-block">
      <input type="text" id="case_date" name="case_date" required  lay-verify="required|date" placeholder="请输入时间（最多15个字）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_date">
    </div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="addformsub">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
   </div>
</form>

</script>
<script type="text/html" id="updateSpecial">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">修改事件信息</legend>
   		 <legend style="font-size: 12px">注意：不能更改事件号</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="updSpecialForm">
  <div class="layui-form-item">
    <label class="layui-form-label">事件号</label>
    <div class="layui-input-block">
      <input type="text" id="u_case_id" name="case_id" required  lay-verify="required|id" placeholder="请输入事件号（最多4位数字且不能以0开头）" autocomplete="off" class="layui-input" disabled="" style="width: 300px">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">事件描述</label>
    <div class="layui-input-block">
      <textarea id="u_case_inf" name="case_inf" required  lay-verify="required|caseInf" placeholder="请描述事件（最多60个字！）" autocomplete="off" class="layui-input" style="width: 300px;height:160px"></textarea>
    </div>
  </div>
 <div class="layui-form-item">
    <label class="layui-form-label">时间</label>
    <div class="layui-input-block">
      <input type="text" id="u_case_date" name="case_date" required  lay-verify="required|date" placeholder="请输入时间（最多15个字）" autocomplete="off" class="layui-input" style="width: 300px">
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