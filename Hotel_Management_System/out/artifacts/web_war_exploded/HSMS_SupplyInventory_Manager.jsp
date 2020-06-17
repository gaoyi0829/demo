<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>库存列表（管理员版）</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="layui/layui.js"></script>
</head>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px; margin-bottom: 50px">
    <legend style="font-size: 40px">库存列表(管理员版)</legend>
</fieldset>
<div class="searchSupp" >
  按库存名搜索：
<form class="layui-form" action="" lay-filter="searchSForm">
  <div class="layui-inline">
    <input class="layui-input"  name="searchSCondition" id="searchSCondition" autocomplete="off" lay-filter="searchSCondition">
  </div>
  <button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">搜索</button>
</form>
</div>
<table class="layui-hide" id="supplyTable" lay-filter="supplyTable"></table>

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
	        	   /^[1-9]\d{0,5}$/
	               , '库存号格式不正确！'
	           ],
	           inventory: [
	        	 //  /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{1,16}$/
	        	   /^.{0,16}$/ 
	               , '库存量格式不正确（最多16字）！'
	           ],
	           name:[
	        	//   /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{1,15}$/
	        		/^.{0,15}$/ 
	        	   ,'库存名格式不正确(最多15个字)！'
	           ],
	          
	           note:[
	        	   // /^[^\x00-\xff]{0,200}$/
	        	   // /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,60}$/
	        	   /^.{0,60}$/ 
	        	   ,'字数最多不超过60！'
	           ]
	          
	    
	         });
		  
	 	   form.on('submit(SformSub)', function(text){
	  		  var searchForm=form.val("searchSForm") 
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
	 			 table.reload('suppReload', {
	 			        page: {
	 			       	curr:$(".layui-laypage-em").next().html()
	 			        //  curr: 1 //重新从第 1 页开始
	 			        }
	 			        ,where: {
	 			          pageName:1,    //默认page
	 		              limitName:1,    //默认limit
	 			          condition:searchForm.searchSCondition
	 			        },
	 			        
	 			      }, 'data');
	  		$('#searchSCondition').val('');
	 		    return false;
	 		  });
	 	   
	    //渲染库存列表				//-----------------------------------------------------------------------------------渲染库存列表
        table.render({
            elem: '#supplyTable' //table的id
            ,url:'supplyList'  //数据接口
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
                ,{field:'supply_id', width:100, title: '库存号', sort: true}
                ,{field:'supply_name', width:200, title: '库存名'}
                ,{field:'supply_inventory', width:120, title: '库存量'}
                ,{field:'supply_note', width:1000, title: '备注'}
            ]]
        	,id:'suppReload'
        	,request:{
            	pageName:'supplyPage',    //默认page
            	limitName:'supplySize'    //默认limit
            }

        });
        
      
      //监听员工列表头工具栏事件	//-----------------------------------------------------------------------------------监听库存列表  头工具栏事件
        table.on('toolbar(supplyTable)', function(obj){
          var form  = layui.form;
          var checkStatus = table.checkStatus(obj.config.id)
          ,data = checkStatus.data //获取选中的数据
          ,ids=[] //传递被选中id的list
          ,name=[]
          ,note=[]
          ,inventory=[];
    
          //------------------------------------------------------------------------------------------------------获取被选中项的各项信息的list
          data.forEach(function(n,i){
              ids.push(n.supply_id);
			  
          });
          data.forEach(function(n,i){
              name.push(n.supply_name);
			  
          });
          data.forEach(function(n,i){
              inventory.push(n.supply_inventory);
			  
          });
          data.forEach(function(n,i){
              note.push(n.supply_note);
			  
          });
        
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
            	      content: $('#addSupply').html(),    
            	      success: function(layero, index){
            	    	  }
            	      })
            	      form.render();   //弹出层加载的content是静态代码，加载后需再渲染一遍form
            	      form.on('submit(addformsub)', function(formData){ 
            	    	 // console.log("111111"+formData);
            	    	 var suppInf=form.val("addSupplyForm")        //获取表单信息   
            	    	 	
              		 	 	console.log(suppInf.supply_id);
                  		   $.post({
                  		    	url:'addSupply',
                  		    	data:{
                 		    		/*'empId':document.getElementById("employee_id").value,
                  		    		'empName':document.getElementById("employee_name").value,
                  		    		'empGender':document.getElementById("employee_gender").value,
                  		    		'empAge':document.getElementById("employee_age").value,
                  		    		'empPosition':document.getElementById("employee_position").value*/
                  		    		'suppId':suppInf.supply_id,
                  		    		'suppName':suppInf.supply_name,
                  		    		'suppInventory':suppInf.supply_inventory,
                  		    		'suppNote':suppInf.supply_note
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
          	      content: $('#updateSupply').html(),
          	      success: function(layero, index){
          	   	 form.render();
          	   	 //打开修改弹出框自动填入员工原本的信息
               	form.val("updSupplyForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
               	  "supply_id": ids // "name": "value"
               	  ,"supply_name": name
               	  ,"supply_inventory": inventory
               	  ,"supply_note": note 
               	 
               	});
          	    }
          	
            });
          	 form.on('submit(updateformsub)', function(text){               //修改信息弹出框的提交按钮监听
          		 var updInf=form.val("updSupplyForm")       //获取表单信息
         		   $.post({
         		    	url:'updateSupply',
         		    	data:{
         		    		'suppId':updInf.supply_id,
          		    		'suppName':updInf.supply_name,
          		    		'suppInventory':updInf.supply_inventory,
          		    		'suppNote':updInf.supply_note,
         		    		
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
                			url:'delSupply',
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

<script type="text/html" id="addSupply">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">添加库存</legend>
	</fieldset>
<form class="layui-form" action="" lay-filter="addSupplyForm">
  <div class="layui-form-item">
    <label class="layui-form-label">库存号</label>
    <div class="layui-input-block">
      <input type="text" id="supply_id" name="supply_id" required  lay-verify="required|id" placeholder="请输入库存号（最多6位数字且不能以0开头）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_id">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">库存名</label>
    <div class="layui-input-block">
      <input type="text" id="supply_name" name="supply_name" required  lay-verify="required|name" placeholder="请输入库存名（最多15个字！）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_name">
    </div>
  </div>
   <div class="layui-form-item">
 <div class="layui-form-item">
    <label class="layui-form-label">库存量</label>
    <div class="layui-input-block">
      <input type="text" id="supply_inventory" name="supply_inventory" required  lay-verify="required|inventory" placeholder="请输入库存量（最多16个字）" autocomplete="off" class="layui-input" style="width: 300px" lay-filter="a_inventory">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea type="text" id="supply_note" name="supply_note" required  lay-verify="note" placeholder="请输入备注（最多60个字！）" autocomplete="off" class="layui-input" style="width: 300px;height:125px" lay-filter="a_note"></textarea>
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
<script type="text/html" id="updateSupply">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; margin-bottom: 30px">
   		 <legend style="font-size: 20px">修改库存信息</legend>
   		 <legend style="font-size: 12px">注意：不能更改库存号</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="updSupplyForm">
  <div class="layui-form-item">
    <label class="layui-form-label">库存号</label>
    <div class="layui-input-block">
      <input type="text" id="u_supply_id" name="supply_id" required  lay-verify="required|id" placeholder="请输入库存号（最多6位数字且不能以0开头）" autocomplete="off" class="layui-input" disabled="" style="width: 300px">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">库存名</label>
    <div class="layui-input-block">
      <input type="text" id="u_supply_name" name="supply_name" required  lay-verify="required|name" placeholder="请输入库存名（最多15个字！）" autocomplete="off" class="layui-input" style="width: 300px">
    </div>
  </div>
 <div class="layui-form-item">
    <label class="layui-form-label">库存量</label>
    <div class="layui-input-block">
      <input type="text" id="u_supply_inventory" name="supply_inventory" required  lay-verify="required|inventory" placeholder="请输入库存量（最多16个字）" autocomplete="off" class="layui-input" style="width: 300px">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
      <textarea type="text" id="u_supply_note" name="supply_note" required  lay-verify="note" placeholder="请输入备注（最多60个字！）" autocomplete="off" class="layui-input" style="width: 300px;height:125px"></textarea>
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