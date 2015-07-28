<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetList(page) {
		document.detailForm.currentPage.value = page;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/app/purchase/getPurchaseList?menu=search" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">주문번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">물품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">물품가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">고객 전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:forEach var="purchase" items="${list }">
	<tr class="ct_list_pop">
		<td align="center">		
		<c:if test="${purchase.tranCode == '1' }">
			<a href="/app/purchase/updatePurchaseView?tranNo=${purchase.tranNo}">${purchase.tranNo}</a>
		</c:if>
		<c:if test="${purchase.tranCode != '1' }">
			${purchase.tranNo}
		</c:if>
		</td>
		<td></td>
		<td align="left">
			<a href="/app/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a>
		</td>
		<td></td>
		<td align="left">${purchase.purchaseProd.price}</td>
		<td></td>
		<td align="left">${purchase.receiverPhone}</td>
		<td></td>
		<td align="left">
			<c:choose>
				<c:when test="${purchase.tranCode == '1' }">
					현재 구매완료 상태입니다.</td><td></td>
					<td align="left"></td>
				</c:when>
				<c:when test="${purchase.tranCode == '2' }">
					현재 배송중 상태입니다.</td><td></td>
					<td align="left">
						<a href="/app/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3">물건도착</a>
					</td>
					<td align="left"></td>
				</c:when>
				<c:otherwise>
					현재 배송완료 상태입니다.</td><td></td>
				</c:otherwise>
			</c:choose>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1">
			<input type="hidden" name="tranCode" value="${product.proTranCode }">
		</td>
	</tr>
	</c:forEach>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<input type="hidden" name="currentPage" value="">
		<jsp:include page="../common/pageNavigator.jsp"/>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>