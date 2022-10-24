<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<title>产品详情页</title>
	<link rel="icon" href="assets/img/favicon.ico">

	<link rel="stylesheet" type="text/css" href="css/webbase.css" />
	<link rel="stylesheet" type="text/css" href="css/pages-item.css" />
	<link rel="stylesheet" type="text/css" href="css/pages-zoom.css" />
	<link rel="stylesheet" type="text/css" href="css/widget-cartPanelView.css" />
</head>

<#--定义扩展属性-->
<#assign custonList=goodsDesc.customAttributeItems? eval >
<#--定义图片-->
<#assign itemImagesList=goodsDesc.itemImages? eval >
<#--定义规格属性-->
<#assign specList=goodsDesc.specificationItems? eval >


<body>

<!--页面顶部 开始-->

<#include "top.ftl">

<!--页面顶部 结束-->
<div class="py-container">
	<div id="item">
		<div class="crumb-wrap">
			<ul class="sui-breadcrumb">
				<li>
					<a href="#">手机、数码、通讯</a>
				</li>
				<li>
					<a href="#">手机</a>
				</li>
				<li>
					<a href="#">Apple苹果</a>
				</li>
				<li class="active">iphone 6S系类</li>
			</ul>
		</div>
		<!--product-info-->
		<div class="product-info">
			<div class="fl preview-wrap">
				<!--放大镜效果-->
				<div class="zoom">
					<!--默认第一个预览-->
					<div id="preview" class="spec-preview">
						<span class="jqzoom"><img style="width: 400px" jqimg="${itemImagesList[0].imageUrl}" src="${itemImagesList[0].imageUrl}" /></span>
					</div>
					<!--下方的缩略图-->
					<div class="spec-scroll">
						<a class="prev">&lt;</a>
						<!--左右按钮-->
						<div class="items">
							<ul>
								<#--									使用freemarker中的for循环-->
								<#list itemImagesList as i>
									<li><img src="${i.imageUrl}" bimg="${i.imageUrl}" onmousemove="preview(this)" /></li>
								</#list>
							</ul>
						</div>
						<a class="next">&gt;</a>
					</div>
				</div>
			</div>
			<div class="fr itemInfo-wrap">
				<div class="sku-name">
					<h4 id="sku-name">${goods.goodsName}</h4>
				</div>
				<div class="news"><span>推荐选择下方[移动优惠购],手机套餐齐搞定,不用换号,每月还有话费返</span></div>
				<div class="summary">
					<div class="summary-wrap">
						<div class="fl title">
							<i>价　　格</i>
						</div>
						<div class="fl price">
							<i>¥</i>
							<em id="price">${goods.price}</em>
							<span>降价通知</span>
						</div>
						<div class="fr remark">
							<i>累计评价</i><em>612188</em>
						</div>
					</div>
					<div class="summary-wrap">
						<div class="fl title">
							<i>促　　销</i>
						</div>
						<div class="fl fix-width">
							<i class="red-bg">加价购</i>
							<em class="t-gray"><span id="caption">${goods.caption}</span></em>
						</div>
					</div>
				</div>
				<div class="support">
					<div class="summary-wrap">
						<div class="fl title">
							<i>支　　持</i>
						</div>
						<div class="fl fix-width">
							<em class="t-gray">以旧换新，闲置手机回收  4G套餐超值抢  礼品购</em>
						</div>
					</div>
					<div class="summary-wrap">
						<div class="fl title">
							<i>配 送 至</i>
						</div>
						<div class="fl fix-width">
							<em class="t-gray">满999.00另加20.00元，或满1999.00另加30.00元，或满2999.00另加40.00元，即可在购物车换购热销商品</em>
						</div>
					</div>
				</div>
				<div class="clearfix choose">
					<div id="specification" class="summary-wrap clearfix">
						<#----------------------------------规格-->
						<#list specList as spec >
							<dl>
								<dt>
									<div class="fl title">
										<i>${spec.attributeName}</i>
									</div>
								</dt>
								<#list spec.attributeValue as value >
									<dd><a name="specOption" href="javascript:checkSpec('${spec.attributeName}','${value}');" >${value}<span title="点击取消选择">&nbsp;</span></a></dd>
								</#list>
							</dl>
						</#list>

					</div>

					<div class="summary-wrap">
						<div class="fl title">
							<div class="control-group">
								<div class="controls">
									<input id="itxt" autocomplete="off" type="text" value="1" minnum="1" class="itxt" />
									<a href="javascript:incrment(1)" class="increment plus">+</a>
									<a href="javascript:incrment(-1)" class="increment mins">-</a>
								</div>
							</div>
						</div>
						<div class="fl">
							<ul class="btn-choose unstyled">
								<li>
									<a href="javascript:addCart()"  class="sui-btn  btn-danger addshopcar">加入购物车</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--product-detail-->
		<div class="clearfix product-detail">
			<div class="fl aside">
				<ul class="sui-nav nav-tabs tab-wraped">
					<li class="active">
						<a href="#index" data-toggle="tab">
							<span>相关分类</span>
						</a>
					</li>
					<li>
						<a href="#profile" data-toggle="tab">
							<span>推荐品牌</span>
						</a>
					</li>
				</ul>
				<div class="tab-content tab-wraped">
					<div id="index" class="tab-pane active">
						<ul class="part-list unstyled">
							<li>手机</li>
							<li>手机壳</li>
							<li>内存卡</li>
							<li>Iphone配件</li>
							<li>贴膜</li>
							<li>手机耳机</li>
							<li>移动电源</li>
							<li>平板电脑</li>
						</ul>
						<ul class="goods-list unstyled">
							<li>
								<div class="list-wrap">
									<div class="p-img">
										<img src="img/_/part01.png" />
									</div>
									<div class="attr">
										<em>Apple苹果iPhone 6s (A1699)</em>
									</div>
									<div class="price">
										<strong>
											<em>¥</em>
											<i>6088.00</i>
										</strong>
									</div>
									<div class="operate">
										<a href="javascript:void(0);" class="sui-btn btn-bordered">加入购物车</a>
									</div>
								</div>
							</li>
							<li>
								<div class="list-wrap">
									<div class="p-img">
										<img src="img/_/part02.png" />
									</div>
									<div class="attr">
										<em>Apple苹果iPhone 6s (A1699)</em>
									</div>
									<div class="price">
										<strong>
											<em>¥</em>
											<i>6088.00</i>
										</strong>
									</div>
									<div class="operate">
										<a href="javascript:void(0);" class="sui-btn btn-bordered">加入购物车</a>
									</div>
								</div>
							</li>
							<li>
								<div class="list-wrap">
									<div class="p-img">
										<img src="img/_/part03.png" />
									</div>
									<div class="attr">
										<em>Apple苹果iPhone 6s (A1699)</em>
									</div>
									<div class="price">
										<strong>
											<em>¥</em>
											<i>6088.00</i>
										</strong>
									</div>
									<div class="operate">
										<a href="javascript:void(0);" class="sui-btn btn-bordered">加入购物车</a>
									</div>
								</div>
								<div class="list-wrap">
									<div class="p-img">
										<img src="img/_/part02.png" />
									</div>
									<div class="attr">
										<em>Apple苹果iPhone 6s (A1699)</em>
									</div>
									<div class="price">
										<strong>
											<em>¥</em>
											<i>6088.00</i>
										</strong>
									</div>
									<div class="operate">
										<a href="javascript:void(0);" class="sui-btn btn-bordered">加入购物车</a>
									</div>
								</div>
								<div class="list-wrap">
									<div class="p-img">
										<img src="img/_/part03.png" />
									</div>
									<div class="attr">
										<em>Apple苹果iPhone 6s (A1699)</em>
									</div>
									<div class="price">
										<strong>
											<em>¥</em>
											<i>6088.00</i>
										</strong>
									</div>
									<div class="operate">
										<a href="javascript:void(0);" class="sui-btn btn-bordered">加入购物车</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div id="profile" class="tab-pane">
						<p>推荐品牌</p>
					</div>
				</div>
			</div>
			<div class="fr detail">
				<div class="clearfix fitting">
					<h4 class="kt">选择搭配</h4>
					<div class="good-suits">
						<div class="fl master">
							<div class="list-wrap">
								<div class="p-img">
									<img src="img/_/l-m01.png" />
								</div>
								<em>￥5299</em>
								<i>+</i>
							</div>
						</div>
						<div class="fl suits">
							<ul class="suit-list">
								<li class="">
									<div id="">
										<img src="img/_/dp01.png" />
									</div>
									<i>Feless费勒斯VR</i>
									<label data-toggle="checkbox" class="checkbox-pretty">
										<input type="checkbox"><span>39</span>
									</label>
								</li>
								<li class="">
									<div id=""><img src="img/_/dp02.png" /> </div>
									<i>Feless费勒斯VR</i>
									<label data-toggle="checkbox" class="checkbox-pretty">
										<input type="checkbox"><span>50</span>
									</label>
								</li>
								<li class="">
									<div id=""><img src="img/_/dp03.png" /></div>
									<i>Feless费勒斯VR</i>
									<label data-toggle="checkbox" class="checkbox-pretty">
										<input type="checkbox"><span>59</span>
									</label>
								</li>
								<li class="">
									<div id=""><img src="img/_/dp04.png" /></div>
									<i>Feless费勒斯VR</i>
									<label data-toggle="checkbox" class="checkbox-pretty">
										<input type="checkbox"><span>99</span>
									</label>
								</li>
							</ul>
						</div>
						<div class="fr result">
							<div class="num">已选购0件商品</div>
							<div class="price-tit"><strong>套餐价</strong></div>
							<div class="price">￥5299</div>
							<button class="sui-btn  btn-danger addshopcar">加入购物车</button>
						</div>
					</div>
				</div>
				<div class="tab-main intro">
					<ul class="sui-nav nav-tabs tab-wraped">
						<li class="active">
							<a href="#one" data-toggle="tab">
								<span>商品介绍</span>
							</a>
						</li>
						<li>
							<a href="#two" data-toggle="tab">
								<span>规格与包装</span>
							</a>
						</li>
						<li>
							<a href="#three" data-toggle="tab">
								<span>售后保障</span>
							</a>
						</li>
						<li>
							<a href="#four" data-toggle="tab">
								<span>商品评价</span>
							</a>
						</li>
						<li>
							<a href="#five" data-toggle="tab">
								<span>手机社区</span>
							</a>
						</li>
					</ul>
					<div class="clearfix"></div>
					<div class="tab-content tab-wraped">




						<div id="one" class="tab-pane active">
							<ul class="goods-intro unstyled">
								<#--------------使用freemarker中的for循环------------->
								<#list custonList as c>
									<li>${c.text}：${c.value}</li>
								</#list>
							</ul>
							<div class="intro-detail">
								${goodsDesc.introduction}
							</div>
						</div>
						<div id="two" class="tab-pane">
							${goodsDesc.packageList}
						</div>
						<div id="three" class="tab-pane">
							${goodsDesc.saleService}
						</div>





						<div id="four" class="tab-pane">
							<p>商品评价</p>
						</div>
						<div id="five" class="tab-pane">
							<p>手机社区</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--like-->
		<div class="clearfix"></div>
		<div class="like">
			<h4 class="kt">猜你喜欢</h4>
			<div class="like-list">
				<ul class="yui3-g">
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike01.png" />
							</div>
							<div class="attr">
								<em>DELL戴尔Ins 15MR-7528SS 15英寸 银色 笔记本</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>3699.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有6人评价</i>
							</div>
						</div>
					</li>
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike02.png" />
							</div>
							<div class="attr">
								<em>Apple苹果iPhone 6s/6s Plus 16G 64G 128G</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>4388.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有700人评价</i>
							</div>
						</div>
					</li>
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike03.png" />
							</div>
							<div class="attr">
								<em>DELL戴尔Ins 15MR-7528SS 15英寸 银色 笔记本</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>4088.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有700人评价</i>
							</div>
						</div>
					</li>
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike04.png" />
							</div>
							<div class="attr">
								<em>DELL戴尔Ins 15MR-7528SS 15英寸 银色 笔记本</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>4088.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有700人评价</i>
							</div>
						</div>
					</li>
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike05.png" />
							</div>
							<div class="attr">
								<em>DELL戴尔Ins 15MR-7528SS 15英寸 银色 笔记本</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>4088.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有700人评价</i>
							</div>
						</div>
					</li>
					<li class="yui3-u-1-6">
						<div class="list-wrap">
							<div class="p-img">
								<img src="img/_/itemlike06.png" />
							</div>
							<div class="attr">
								<em>DELL戴尔Ins 15MR-7528SS 15英寸 银色 笔记本</em>
							</div>
							<div class="price">
								<strong>
									<em>¥</em>
									<i>4088.00</i>
								</strong>
							</div>
							<div class="commit">
								<i class="command">已有700人评价</i>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 底部栏位 -->

<!--页面底部  开始 -->

<#include "foot.ftl">
<!--页面底部  结束 -->
</body>

<script>

	//缓存到js页面中
	var skuList =[
		<#list item as items>
		{
			"id":${items.id?c},
			"title":"${items.title}",
			"sellPoint":"${items.sellPoint}",
			"price":${items.price?c},
			"spec":${items.spec}

		},
		</#list>
	]


	//定义一个map  用来存放a标签中的key和value值
	var map={};
	//定义一个全局变量接收选中的itemId
	var itemId = -1;

	function checkSpec(key,value) {
		// 往map里赋值
		map[key]=value;
		// 取出map中所有的value值
		var values=Object.values(map);

		//根据名称选择器获取所有的a标签 然后遍历
		$("[name=specOption]").each(function (i,e) {
			//判断text值在不在map里面，如果在的话就进行打钩
			if(values.indexOf($(e).text().trim()) >=0){
				$(e).attr('class','selected')
			}else {
				$(e).attr('class','')
			}
		})
		console.log(values);

		for (var i = 0; i <skuList.length ; i++) {
			if (equMap(skuList[i].spec,map)){
				$("#sku-name").html(skuList[i].title);
				$("#price").html(skuList[i].price);
				$("#caption").html(skuList[i].caption);
				itemId = skuList[i].id;
				break;
			}
		}

	}


	//判断两个map是否相等
	function equMap(m1,m2) {
		if (Object.values(m1).length != Object.values(m2).length){
			return false;
		}
		//for in来遍历map
		for(var key in m1){
			if (m1[key] != m2[key]){
				return false;
			}
		}
		return true;
	}

	var sum=1;
	function incrment(num) {
		if (sum==1 && num<0){
			return;
		}
		sum+=num;
		$("#itxt").val(sum);
	}

	function addCart() {
		var num = $("#itxt").val();
		if (itemId == -1){
			alert("请选择要购买的商品")
			return;
		}
		location.href="http://localhost:3000/cart/addcart?itemId="+itemId+"&num="+num;
	}

</script>

</html>