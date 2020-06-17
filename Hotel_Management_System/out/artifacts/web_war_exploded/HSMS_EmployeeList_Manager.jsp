<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>员工列表（管理员版）</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="layui/layui.js"></script>
<%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">--%>
<%--    <script src="${pageContext.request.contextPath}/layui/jquery-3.4.1.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>--%>
    <!-- <script src="./layui/layui.js"></script> -->
    
</head>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px; margin-bottom: 50px">
    <legend style="font-size: 40px">员工列表(管理员版)</legend>
</fieldset>
<div class="searchEmp" >
  按员工号搜索：

<form class="layui-form" action="" lay-filter="searchEForm">
  <div class="layui-inline">
    <input class="layui-input"  name="searchECondition" id="searchECondition" autocomplete="off" lay-filter="searchECondition">
  </div>
  <button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">搜索</button>
</form>


<table class="layui-hide" id="empsTable" lay-filter="empsTable"></table>

<script type="text/html" id="operation1">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
    <a class="layui-btn  layui-btn-xs" style="background-color: gold ;color: black" lay-event="suggest">我有意见/建议</a>
    <a class="layui-btn  layui-btn-xs" lay-event="praise">好评</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="complaint">我要投诉</a>
</script>
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
	        	   /^[1-9]\d{0,6}$/
	               , '员工号格式不正确！'
	           ],
	           age: [
	               /^[1-9]\d{1,1}$/
	               , '请输入正确的员工年龄！'
	           ],
	           name:[
	        	   /^[^\x00-\xff]{2,6}$/      
	        	   ,'员工姓名格式不正确！'
	           ],
	           position:[
	        	   // /^[^\x00-\xff]{0,8}$/
	        	    /^.{0,8}$/ 
	        	   // /^[\u4e00-\u9fa5]{1,8}$/
	        	   ,'员工职位格式不正确！'
	           ],
	           scp:[
	        	   // /^[^\x00-\xff]{0,200}$/
	        	   // /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,200}$/
	        	    /^.{0,200}$/ 
	        	   ,'字数最多不超过200！'
	           ]
	          
	    
	         });
	    //--------------------------------------------------------------------------监听搜索按钮
	 	   form.on('submit(SformSub)', function(text){
	  		  var searchForm=form.val("searchEForm") 
	 		 //   layer.msg("searchForm.searchECondition:"+searchForm.searchECondition)
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
	 			 table.reload('empsReload', {
	 			        page: {
	 			         // curr: 1 //重新从第 1 页开始
	 			         	curr:$(".layui-laypage-em").next().html()
	 			        }
	 			        ,where: {
	 			          pageName:1,    //默认page
	 		              limitName:1,    //默认limit
	 			          condition:   searchForm.searchECondition
	 			        },
	 			        
	 			      }, 'data');
	  		$('#searchECondition').val('');
	 		    return false;
	 		  });



	    //渲染员工列表				//-----------------------------------------------------------------------------------渲染员工列表
        table.render({
            elem: '#empsTable' //table的id
            ,url:'empsList'  //数据接口
            ,page: true //开启分页
            ,limit:10
            ,limits:[10]
        	,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
         
            ,cols: [[//表头		
            	 {type: 'checkbox', fixed: 'left'}
                ,{field:'employee_id', width:150, title: '员工号', sort: true}
                ,{field:'employee_name', width:150, title: '员工姓名'}
                ,{field:'employee_gender', width:80, title: '性别', sort: true}
                ,{field:'employee_age', width:80, title: '年龄',sort:true}
                ,{field:'employee_position', width:150, title: '员工职位'}
                ,{fixed: 'right', width: 500, align:'center', toolbar: '#operation1'}
            ]]
       		 ,where: {condition:''}
       		,id:'empsReload'
        	,request:{
            	pageName:'empsPage',    //默认page
            	limitName:'empsSize'    //默认limit
            }

        });
        
      
      //监听员工列表头工具栏事件	//-----------------------------------------------------------------------------------------监听员工列表  头工具栏事件
        table.on('toolbar(empsTable)', function(obj){
          var form  = layui.form;
          var checkStatus = table.checkStatus(obj.config.id)
          ,data = checkStatus.data //获取选中的数据
          ,ids=[] //传递被选中id的list
          ,name=[]
          ,gender=[]
          ,position=[]
          ,age=[];
          //---------------------------------------------------------------------------------------------------------------获取被选中项的各项信息的list
          data.forEach(function(n,i){
              ids.push(n.employee_id);
			  
          });
          data.forEach(function(n,i){
              name.push(n.employee_name);
			  
          });
          data.forEach(function(n,i){
              age.push(n.employee_age);
			  
          });
          data.forEach(function(n,i){
              gender.push(n.employee_gender);
			  
          });
          data.forEach(function(n,i){
              position.push(n.employee_position);
			  
          });
          console.log(ids)
		  console.log(typeof ids)
          var ayon1=-1,
          dyon1=-1;
          switch(obj.event){
            case 'add':					//-----------------------------------------------------------------------------------头工具点击添加
            	  var index=layer.open({//-----------------------------------------------------------------------------------弹出添加员工弹出层
            		  closeBtn: 1,
            	      type: 1,
            	      area: ['500px', '500px'],
            	      shadeClose: true, //点击遮罩关闭
            	      content: $('#addEmps').html(),
            	      success: function(layero, index){
            	    	  }
            	      })
            	      form.render();   //弹出层加载的content是静态代码，加载后需再渲染一遍form
            	      form.on('submit(addformsub)', function(formData){
            	    	 // console.log("111111"+formData);
            	    	 var empsInf=form.val("addEmpsTable")       //获取表单信息   
            	    	 	
              		 	 	console.log(empsInf.employee_id);
                  		   $.post({
                  		    	url:'addEmployee',
                  		    	data:{
                 		    		/*'empId':document.getElementById("employee_id").value,
                  		    		'empName':document.getElementById("employee_name").value,
                  		    		'empGender':document.getElementById("employee_gender").value,
                  		    		'empAge':document.getElementById("employee_age").value,
                  		    		'empPosition':document.getElementById("employee_position").value*/
                  		    		'empId':empsInf.employee_id,
                  		    		'empName':empsInf.employee_name,
                  		    		'empGender':empsInf.employee_gender,
                  		    		'empAge':empsInf.employee_age,
                  		    		'empPosition':empsInf.employee_position
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
                                    table.reload('empsReload', {
                                        page: {
                                            curr:$(".layui-laypage-em").next().html()
                                            //curr:praseTable.config.page.curr
                                        }
                                        ,where: {
                                            // pageName:1,    //默认page
                                            // limitName:10,    //默认limit
                                            // num:data.employee_id
                                        },

                                    }, 'data');
                          		 // window.location.reload();// 刷新页面
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
            case 'update':		//----------------------------------------------------------------------------------------头工具点击修改
              if(data.length === 0){
                layer.msg('请选择一行');
              } else if(data.length > 1){
                layer.msg('只能同时编辑一个');
              } else {
            				  //------------------------------------------------------------------------------------------弹出修改信息弹出层
          	  var layerIndex1=layer.open({
          		  closeBtn: 1,
          	      type: 1,
          	      area: ['500px', '500px'],
          	      shadeClose: true, //点击遮罩关闭
          	      content: $('#updateEmployee').html(),
          	      success: function(layero, index){
          	   	 form.render();
          	   	 //打开修改弹出框自动填入员工原本的信息
               	form.val("updEmpsTable", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
               	  "employee_id": ids // "name": "value"
               	  ,"employee_name": name
               	  ,"employee_age": age
               	  ,"employee_gender": gender
               	  ,"employee_position": position
               	});
          	    }
          	
            });
          	 form.on('submit(updateformsub)', function(text){               //修改信息弹出框的提交按钮监听
          		 var updInf=form.val("updEmpsTable")       //获取表单信息

         		   $.post({
         		    	url:'updateEmployee',
         		    	data:{
         		    		'empId':updInf.employee_id,
          		    		'empName':updInf.employee_name,
          		    		'empGender':updInf.employee_gender,
          		    		'empAge':updInf.employee_age,
          		    		'empPosition':updInf.employee_position
         		    		
         		    		//'txt':encodeURI(document.getElementById("txt").value)
         		   			// 'txt':JSON.stringify(text.field)
         		    	},
         		 	 	 success:function(data){
          				  console.log(data);
          		  }
         		    })	
         		  
         		    layer.close(layerIndex1); //再执行关闭
         		     //window.location.reload();// 刷新页面
                 table.reload('empsReload', {
                     page: {
                         curr:$(".layui-laypage-em").next().html()
                         //curr:praseTable.config.page.curr
                     }
                     ,where: {
                         // pageName:1,    //默认page
                         // limitName:10,    //默认limit
                         // num:data.employee_id
                     },

                 }, 'data');
         		    layer.msg('修改成功！');
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
                			url:'delEmployee',
                			data:
                				"ids="+ids,
                			success:function(dyon){
                				dyon1=dyon;
              		 	 		console.log("dyon=========="+typeof dyon);
              		 	 		console.log("dyon1=========="+typeof dyon1);
              		 	 		console.log("dyon=========="+dyon);
              		 	 		console.log("dyon1=========="+dyon1);
              		 	 	if(dyon1==1){
								layer.msg('删除成功！');
                                table.reload('empsReload', {
                                    page: {
                                        curr:$(".layui-laypage-em").next().html()

                                        //curr:praseTable.config.page.curr
                                    }
                                    ,where: {
                                        // pageName:1,    //默认page
                                        // limitName:10,    //默认limit
                                        // num:data.employee_id
                                    },

                                }, 'data');
								//window.location.reload();// 刷新页面
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
        //监听行工具事件				//-----------------------------------------------------------------------------------监听行工具事件
        table.on('tool(empsTable)', function(obj){ 
        	//注：tool 是工具条事件名，empsTable 是 table 原始容器的属性 lay-filter="对应的值"
          var data = obj.data //获得当前行数据
          ,layEvent = obj.event; //获得 lay-event 对应的值
          
          
        
          if(layEvent === 'detail')		//-------------------------------------------------------------------------------行工具点击查看详情
          {
        	  
        							//-----------------------------------------------------------------------------------弹出详情弹出层
        	  layerIndex=layer.open({
        		  closeBtn: 1,
        	      type: 1,
        	      area: ['1400px', '700px'],
        	      shadeClose: true, //点击遮罩关闭
        	      content: $('#detail').html(),
        	      success: function(layero, index){
        	    	  //praiseTable渲染
        	    	  praiseTable=table.render({
        	          	elem:'#praiseTable'
        	          	,url:'empsPraise'
        	          	,page:true
        	          	,limit:10
        	          	,limits:[10]
        	          	,where: {num:data.employee_id}
        	          	,cols:[[ 
        	         		  {field:'praise_inf', width:1000, title: '内容'}
        	                 ,{field:'praise_date', width:200, title: '日期'}
        	                 ,{fixed: 'right', width:150, align:'center', toolbar: '#operation2'}
        	             

        	          	]]
        	          	,id:'praReload'
        	    	    ,request:{
        	            	pageName:'praisePage',    //默认page
        	            	limitName:'praiseSize',    //默认limit
        	            }
        	    	  
        	          })
        	          //SuggestionTable渲染
        	          table.render({
        	          	elem:'#suggestionTable'
        	          	,url:'empsSuggestion'
        	          	,page:true
        	          	,limit:10
        	          	,limits:[10]
        	          	,where: {num:data.employee_id}
        	        	,id:'sugReload'
        	          	,cols:[[ 
        	          		  {field:'suggestion_inf', width:1000, title: '内容'}
        	                 ,{field:'suggestion_date', width:200, title: '日期'}
        	                 ,{fixed: 'right', width:150, align:'center', toolbar: '#operation2'}

        	          	]]
        	        
        	    	  ,request:{
        	            	pageName:'suggestionPage',    //默认page
        	            	limitName:'suggestionSize',    //默认limit
        	            }
        	    	  
        	          })
        	   
        	          //ComplaintTable渲染
        	          table.render({
        	          	elem:'#complaintTable'
        	          	,url:'empsComplaint'
        	          	,page:true
        	          	,limit:10
        	          	,limits:[10]
        	          	,where: {num:data.employee_id}
        	        	,id:'compReload'
        	          	,cols:[[ 
        	          		  {field:'complaint_inf', width:1000, title: '内容'}
        	                 ,{field:'complaint_date', width:200, title: '日期'}
        	                 ,{fixed: 'right', width:150, align:'center', toolbar: '#operation2'}

        	          	]]
        	    	  ,request:{
        	            	pageName:'complaintPage',    //默认page
        	            	limitName:'complaintSize',    //默认limit
        	            }
        	    	  
        	          })
        	          
        	          
        	    }
        	 
          });
        	//监听弹出层三个表的行工具	//-----------------------------------------------------------------------------------监听弹出层三个表的行工具
        	  //praiseTable
        	  table.on('tool(praiseTable)', function(obj1){ //注：tool 是工具条事件名，empsTable 是 table 原始容器的属性 lay-filter="对应的值"
                  var data = obj1.data //获得当前行数据
                  ,layEventP = obj1.event //获得 lay-event 对应的值
                  ,pyon=-1;
        	 //  layer.msg(layEventP);
                  if(layEventP=='del'){//--------------------------------------------------------------------------------点击弹出层的行工具中的删除
                	  layer.confirm('确定删除所选项吗？',function(index){
                  		$.post({
                  			url:'delPraise',
                  			data:{
                  				'praise_inf':data.praise_inf,
                  				'praise_date':data.praise_date
                  			},
                  			success:function(pyon){
                  				pyon1=pyon;
                		 	 		console.log("pyon=========="+typeof pyon);
                		 	 		console.log("pyon1=========="+typeof pyon1);
                		 	 		console.log("pyon=========="+pyon);
                		 	 		console.log("pyon1=========="+pyon1);
                		 	 	if(pyon1==1){
  								layer.msg('删除成功！');
  								//----------------------------------------------------------------------------------------重载当前页面
  							
  								 table.reload('praReload', {
  							        page: {
  							        	curr:$(".layui-laypage-em").next().html()
  							        	//curr:praseTable.config.page.curr
  							        	}
  							        ,where: {
  							          pageName:1,    //默认page
  						              limitName:10,    //默认limit
  							          num:data.employee_id
  							        },
  							         
  							      }, 'data');
  								 
                      			}else if(pyon1==0){
                      			  layer.msg('该项已被删除,您可以继续删除,或者重新打开页面刷新数据')
                      			}else{
                      				layer.msg("error");
                      			}
                  			}
                  		})
                  	})
                  }else{
                	  layer.msg('error')
                  }
                  
        	  });
        	  //SuggestionTable
        	  table.on('tool(suggestionTable)', function(obj2){ 
        		  //注：tool 是工具条事件名，empsTable 是 table 原始容器的属性 lay-filter="对应的值"
                  var data = obj2.data //获得当前行数据
                  ,layEventS = obj2.event //获得 lay-event 对应的值
                  ,syon=-1;
        	 //  layer.msg(layEventP);
                  if(layEventS=='del'){
                	  layer.confirm('确定删除所选项吗？',function(index){
                  		$.post({
                  			url:'delSuggestion',
                  			data:{
                  				'suggestion_inf':data.suggestion_inf,
                  				'suggestion_date':data.suggestion_date
                  			},
                  			success:function(syon){
                  				syon1=syon;
                		 	 		console.log("syon=========="+typeof syon);
                		 	 		console.log("syon1=========="+typeof syon1);
                		 	 		console.log("syon=========="+syon);
                		 	 		console.log("syon1=========="+syon1);
                		 	 	if(syon1==1){
  								layer.msg('删除成功！');
 								 table.reload('sugReload', {
   							        page: {
   							        	curr:$(".layui-laypage-em").next().html()
   							        	//curr:praseTable.config.page.curr
   							        	}
   							        ,where: {
   							          pageName:1,    //默认page
   						              limitName:10,    //默认limit
   							          num:data.employee_id
   							        },
   							         
   							      }, 'data');
                      			}else if(syon1==0){
                      			  layer.msg('该项已被删除,您可以继续删除,或者重新打开页面刷新数据')
                      			}else{
                      				layer.msg("error");
                      			}
                  			}
                  		})
                  	})
                  }else{
                	  layer.msg('error')
                  }
                  
        	  });
        	//ComplaintTable
        	  table.on('tool(complaintTable)', function(obj3){ //注：tool 是工具条事件名，empsTable 是 table 原始容器的属性 lay-filter="对应的值"
                  var data = obj3.data //获得当前行数据
                  ,layEventC = obj3.event //获得 lay-event 对应的值
                  ,cyon=-1;
        	 //  layer.msg(layEventP);
                  if(layEventC=='del'){
                	  layer.confirm('确定删除所选项吗？',function(index){
                  		$.post({
                  			url:'delComplaint',
                  			data:{
                  				'complaint_inf':data.complaint_inf,
                  				'complaint_date':data.complaint_date
                  			},
                  			success:function(cyon){
                  				cyon1=cyon;
                		 	 		console.log("cyon=========="+typeof cyon);
                		 	 		console.log("cyon1=========="+typeof cyon1);
                		 	 		console.log("cyon=========="+cyon);
                		 	 		console.log("cyon1=========="+cyon1);
                		 	 	if(cyon1==1){
  								layer.msg('删除成功!');
 								 table.reload('compReload', {
   							        page: {
   							        	curr:$(".layui-laypage-em").next().html()
   							        	//curr:praseTable.config.page.curr
   							        	}
   							        ,where: {
   							          pageName:1,    //默认page
   						              limitName:10,    //默认limit
   							          num:data.employee_id
   							        },
   							         
   							      }, 'data');
                      			}else if(cyon1==0){
                      			  //layer.msg('该项已被删除,您可以继续删除,或者重新打开页面刷新数据')
                      			}else{
                      				layer.msg("error");
                      			}
                  			}
                  		})
                  	})
                  }else{
                	  layer.msg('error')
                  }
                  
        	  });
          }

          //GiveSuggestion			//--------------------------------------------------------------------------员工列表行工具发表建议
           else if(layEvent === 'suggest')
          {
        	   var index=layer.open({
        		  closeBtn: 1,
         	      type: 1,
         	      area: ['700px', '400px'],
         	      shadeClose: true, //点击遮罩关闭
         	      content: $('#suggest').html(),
         	      
        		 });
        	   form.on('submit(formSubmit)', function(text){
        		    
        		   $.post({
        		    	url:'giveSuggestion',
        		    	data:{
        		    		'num':data.employee_id,
        		    		'txt':document.getElementById("suggestiontxt").value
        		    		
        		    		//'txt':encodeURI(document.getElementById("txt").value)
        		   			// 'txt':JSON.stringify(text.field)
        		    	},
        		 	 	 success:function(data){
         				  console.log(data);
         		  }
        		    })	
        		    layer.msg('发表成功！');
        		    layer.close(index); //再执行关闭
        		    return false;
        		  });
          }
          //GivePraise				//--------------------------------------------------------------------------员工列表行工具发表赞赏
        	 else if(layEvent === 'praise'){
        		 var index=layer.open({
           		  closeBtn: 1,
            	      type: 1,
            	      area: ['700px', '400px'],
            	      shadeClose: true, //点击遮罩关闭
            	      content: $('#praise').html(),
            	      
           		 });
           	   form.on('submit(formSubmit)', function(text){
           		    
           		   $.post({
           		    	url:'givePraise',
           		    	data:{
           		    		'num':data.employee_id,
           		    		'txt':document.getElementById("praisetxt").value
           		    		
           		    		//'txt':encodeURI(document.getElementById("txt").value)
           		   			// 'txt':JSON.stringify(text.field)
           		    	},
           		 	 	 success:function(data){
            				  console.log(data);
            		  }
           		    })	
           		    layer.msg('发表成功！');
           		    layer.close(index); //再执行关闭
           		    return false;
           		  });
          } else if(layEvent === 'complaint'){//--------------------------------------------------------------------------员工列表行工具投诉
        	  //GiveComplaint
        	  var index=layer.open({
           		  closeBtn: 1,
            	      type: 1,
            	      area: ['700px', '400px'],
            	      shadeClose: true, //点击遮罩关闭
            	      content: $('#complaint').html(),
            	      
           		 });
           	   form.on('submit(formSubmit)', function(text){
           		    
           		   $.post({
           		    	url:'giveComplaint',
           		    	data:{
           		    		'num':data.employee_id,
           		    		'txt':document.getElementById("complainttxt").value
           		    		
           		    		//'txt':encodeURI(document.getElementById("txt").value)
           		   			// 'txt':JSON.stringify(text.field)
           		    	},
           		 	 	 success:function(data){
            				  console.log(data);
            		  }
           		    })	
           		    layer.msg('发表成功！');
           		    layer.close(index); //再执行关闭
           		    return false;
           		  });
          }
        });
    });
    
  
</script>
<!-- Detail3Table -->
<script type="text/html" id="detail">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">TA受到的赞赏</legend>
	</fieldset>
	<table class="layui-hide" id="praiseTable" lay-filter="praiseTable"></table>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">TA收到的建议</legend>
	</fieldset>
	<table class="layui-hide" id="suggestionTable" lay-filter="suggestionTable"></table>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">TA收到的投诉</legend>
	</fieldset>
	<table class="layui-hide" id="complaintTable" lay-filter="complaintTable"></table>
</script>
<!-- GiveSuggest -->
<script type="text/html" id="suggest">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">我有意见/建议</legend>
	</fieldset>
	<form class="layui-form" action="springmvc/empsSuggest"  method="post">
		<div class="layui-form-item" pane>
   			<textarea id="suggestiontxt" name="suggestTxt" required lay-verify="required|scp" placeholder="我的建议是.......(最多不超过200字)" class="layui-textarea"></textarea>
		<div class="layui-input-block">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formSubmit" style="margin-top: 50px">立即提交</button>
			</div>
 	     </div>
        </div>
        </div>
    </form>
</script>
<script type="text/html" id="praise">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">好评</legend>
	</fieldset>
	<form class="layui-form" action=""  method="post">
		<div class="layui-form-item" pane>
   			<textarea id="praisetxt" name="praiseTxt" required lay-verify="required|scp" placeholder="我该怎么夸TA呢........(最多不超过200字)" class="layui-textarea"></textarea>
		<div class="layui-input-block">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formSubmit" style="margin-top: 50px">立即提交</button>
			</div>
 	 </div>
        </div>
        </div>
    </form>
</script>
<script type="text/html" id="complaint">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">投诉</legend>
	</fieldset>
	<form class="layui-form" action=""  method="post">
		<div class="layui-form-item" pane>
   			<textarea id="complainttxt" name="complaintTxt" required lay-verify="required|scp" placeholder="这个家伙气死我了........(最多不超过200字)" class="layui-textarea"></textarea>
		<div class="layui-input-block">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formSubmit" style="margin-top: 50px">立即提交</button>
			</div>
 	 </div>
        </div>
        </div>
	</form>
</script>
<script type="text/html" id="addEmps">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">添加员工</legend>
	</fieldset>
<form class="layui-form" action="" lay-filter="addEmpsTable">
  <div class="layui-form-item">
    <label class="layui-form-label">员工号</label>
    <div class="layui-input-block">
      <input type="text" id="employee_id" name="employee_id" required  lay-verify="required|id" placeholder="请输入员工号（最多7位数字且不能以0开头）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_id">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">员工姓名</label>
    <div class="layui-input-block">
      <input type="text" id="employee_name" name="employee_name" required  lay-verify="required|name" placeholder="请输入员工姓名（最多6个汉字！）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_name">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block" style="width: 300px">   
      <select id="employee_gender" name="employee_gender" lay-verify="required"  lay-filter="a_gender">
        <option value=""></option>
        <option value="男">男</option>
        <option value="女">女</option>
      </select>
    </div>
  </div>
 <div class="layui-form-item">
    <label class="layui-form-label">年龄</label>
    <div class="layui-input-block">
      <input type="text" id="employee_age" name="employee_age" required  lay-verify="required|age" placeholder="请输入员工年龄" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_age">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">职位</label>
    <div class="layui-input-block">
      <input type="text" id="employee_position" name="employee_position" required  lay-verify="required|position" placeholder="请输入员工职位（最多8个字！）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_positiron">
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
<script type="text/html" id="updateEmployee">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">修改员工信息</legend>
   		 <legend style="font-size: 12px">注意：不能更改员工号</legend>

	</fieldset>
<form class="layui-form" action="" lay-filter="updEmpsTable">
  <div class="layui-form-item">
    <label class="layui-form-label">员工号</label>
    <div class="layui-input-block">
      <input type="text" id="u_employee_id" name="employee_id" required  lay-verify="required|id" placeholder="请输入员工号（最多7位数字且不能以0开头）" autocomplete="off" class="layui-input" disabled="" style="width: 300px">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">员工姓名</label>
    <div class="layui-input-block">
      <input type="text" id="u_employee_name" name="employee_name" required  lay-verify="required|name" placeholder="请输入员工姓名（最多6个汉字！）" autocomplete="off" class="layui-input" style="width: 300px">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block" style="width: 300px">
      <select id="u_employee_gender" name="employee_gender" lay-verify="required" >
        <option value=""></option>
        <option value="男">男</option>
        <option value="女">女</option>
      </select>
    </div>
  </div>
 <div class="layui-form-item">
    <label class="layui-form-label">年龄</label>
    <div class="layui-input-block">
      <input type="text" id="u_employee_age" name="employee_age" required  lay-verify="required|age" placeholder="请输入员工年龄" autocomplete="off" class="layui-input" style="width: 300px">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">职位</label>
    <div class="layui-input-block">
      <input type="text" id="u_employee_position" name="employee_position" required  lay-verify="required|position" placeholder="请输入员工职位（最多8个汉字！）" autocomplete="off" class="layui-input" style="width: 300px">
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
</div>
</body>
</html>