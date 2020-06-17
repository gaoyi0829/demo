<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>员工列表</title>
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="layui/layui.js"></script>
</head>
<style>
	#carousel_guest{
		text-align: center;
		line-height: 500px;
		font-size: x-large;


	}
</style>
<body class="layui-form-contenner" style="margin-left:2%;margin-right:2%;margin-top:3%">

<button class="layui-btn"  lay-filter="back" id="back" style="float: right"
		onclick="location.href='http://localhost:8080/Hotel_Management_System_war_exploded/HotelHome.html'">返回首页</button>

<fieldset class="layui-elem-field layui-field-title">
	<legend style="font-size: 40px">员工列表</legend>
</fieldset>
<div class="layui-carousel" id="carousel_guest" lay-filter="carousel_guest" >
	<div carousel-item="">
		<div style="background: lightpink">华信酒店欢迎您的光临！</div>
		<div style="background: #5FB878">如果我们让您心情愉悦了可以给我们您宝贵的好评！</div>
		<div style="background: #FF5622">如果我们影响了您美好的心情，请说出您的不满！</div>
		<div style="background: #FEB800">如果可以，您还可以提出您宝贵的建议！</div>
	</div>
</div>
<div class="searchEmp" >
	按员工号搜索：
	<form class="layui-form" action="" lay-filter="searchEForm">
		<div class="layui-inline">
			<input class="layui-input"  name="searchECondition" id="searchECondition" autocomplete="off" lay-filter="searchECondition">
		</div>
		<button class="layui-btn" lay-submit lay-filter="SformSub" id="SformSub">搜索</button>
	</form>
</div>
</form>

<script type="text/javascript">
	/*
        function searchEmp(){

            layui.use(['layer','table'], function(){
                var layer = layui.layer,
                table=layui.table;
                 var eCondition=form.val("addEmpsTable")       //获取表单信息
                //layer.msg("hha")
                 table.reload('empsReload', {
                        page: {
                          curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                             condition:""
                        }
                      }, 'data');
              });


        }*/
</script>
<table class="layui-hide" id="empsTable" lay-filter="empsTable"></table>

<script type="text/html" id="operation">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
	<a class="layui-btn  layui-btn-xs" style="background-color: gold ;color: black" lay-event="suggest">我有意见/建议</a>
	<a class="layui-btn  layui-btn-xs" lay-event="praise">好评</a>
	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="complaint">我要投诉</a>
</script>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>

	layui.use(['table','layer','form','carousel'], function(){
		var table = layui.table;
		var layer = layui.layer;
		var form  = layui.form;
		var carousel  = layui.carousel;
		$ = layui.jquery;


		form.verify({

			id:[
				/^[1-9]\d{0,6}$/
				, '员工号格式不正确！'
			],
			scp:[
				// /^[^\x00-\xff]{0,200}$/
				// /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,200}$/
				//  /([\s\S]*){0,200}$/
				/^.{0,200}$/
				,'字数最多不超过200！'
			]


		});
		/*  $('#searchEBtn').on('click', function(){
               var eCondition=form.val("searchEForm");       //获取表单信息
                  console.log("eCondition"+eCondition)
                    });*/
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
					//   curr: 1 //重新从第 1 页开始
					curr:$(".layui-laypage-em").next().html()
				}
				,where: {
					pageName:1,    //默认page
					limitName:1,    //默认limit
					condition:searchForm.searchECondition
				},

			}, 'data');
			$('#searchECondition').val('');
			return false;

		});
		table.render({
			elem: '#empsTable' //table的id
			,url:'empsList'  //数据接口
			,page: true //开启分页
			,limit:10
			,limits:[10]
			,cols: [[//表头
				{field:'employee_id', width:150, title: '员工号',sort: false}
				,{field:'employee_name', width:150, title: '员工姓名',sort: false}
				,{field:'employee_gender', width:80, title: '性别',sort: false}
				,{field:'employee_age', width:80, title: '年龄',sort: false}
				,{field:'employee_position', width:150, title: '员工职位',sort: false}
				,{fixed: 'right', width: 500, align:'center', toolbar: '#operation'}
			]]
			,where: {condition:''}
			,id:'empsReload'
			,request:{
				pageName:'empsPage',    //默认page
				limitName:'empsSize'    //默认limit
			}

		});

		carousel.render({
			elem: '#carousel_guest'
			,width: '100%'
			,height: '540px'
			,arrow: 'always'
		});


		//监听行工具事件
		table.on('tool(empsTable)', function(obj){ //注：tool 是工具条事件名，empsTable 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
					,layEvent = obj.event; //获得 lay-event 对应的值
			if(layEvent === 'detail')
			{
				//将数据传入controller
				/* $.ajax({
                     url:'empsPraise',
                     data:{'num':data.employee_id},
                     success:function(data){
                         console.log(data);
                     }
                 })*/

				//弹出弹出层
				layerIndex=layer.open({
					closeBtn: 1,
					type: 1,
					area: ['1400px', '700px'],
					shadeClose: true, //点击遮罩关闭
					content: $('#detail').html(),
					success: function(layero, index){
						//praiseTable渲染
						table.render({
							elem:'#praiseTable'
							,url:'empsPraise'
							,page:true
							,limit:10
							,limits:[10]
							,where: {num:data.employee_id}
							,cols:[[
								{field:'praise_inf', width:1150, title: '内容'}
								,{field:'praise_date', width:200, title: '日期'}


							]]
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
							,cols:[[
								{field:'suggestion_inf', width:1150, title: '内容'}
								,{field:'suggestion_date', width:200, title: '日期'}


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
							,cols:[[
								{field:'complaint_inf', width:1150, title: '内容'}
								,{field:'complaint_date', width:200, title: '日期'}


							]]
							,request:{
								pageName:'complaintPage',    //默认page
								limitName:'complaintSize',    //默认limit
							}

						})
					}
				});
			}

			//GiveSuggestion
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
			//GivePraise
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
			} else if(layEvent === 'complaint'){
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
</body>
</html>