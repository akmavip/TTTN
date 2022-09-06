<%@ page pageEncoding="utf-8"%>
<style id="cart-effect"></style>
<div class="panel panel-danger nn-cart">
	<div class="panel-heading">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-shopping-cart"></span>Giỏ hàng
		</h3>
	</div>
	<div class="panel-body">
		<div class="col-xs-5" data_product_cout="${cart.countCart}">
			<a href="/cart/view">
				<img id="cart-img" src="/static/images/shopping-cart.jpg">
			</a>
		</div>
	</div>
</div>
