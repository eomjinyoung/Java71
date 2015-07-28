<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetList(currentPage){
	document.detailForm.currentPage.value = currentPage
	document.detailForm.submit();
}

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
	<form name="detailForm" action="/app/purchase/getSaleList?menu=manage" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						판매된 상품 관리					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		
				<option value="0"${search.searchCondition == '0' ? 'selected' : ''}>상품번호</option>
				<option value="1"${search.searchCondition == '1' ? 'selected' : ''}>상품명</option>
				<option value="2"${search.searchCondition == '2' ? 'selected' : ''}>상품가격</option>
		
			</select>		
			<input type="text" name="searchKeyword" value="${!empty search.searchKeyword ? search.searchKeyword : ''}" 
			class="ct_input_g" style="width:200px; height:19px" />			
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetSaleList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매자</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매일시</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:forEach var="purchase" items="${list }">
	<tr class="ct_list_pop">
		<td align="center">${purchase.purchaseProd.prodNo}</td>
		<td></td>
		
			<td align="left"><a href="/app/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}&menu=manage">${purchase.purchaseProd.prodName}</a></td>
		
		<td></td>
		<td align="left">${purchase.purchaseProd.price}</td>
		<td></td>
		<td align="left">${purchase.purchaseProd.regDate}</td>
		<td></td>
		<td align="left">${purchase.buyer.userId}</td>
		<td></td>
		<td align="left">${purchase.divyDate}</td>
		<td></td>
		
			<c:choose>
				<c:when test="${purchase.tranCode == '1' }">
					<td align="left">구매완료&nbsp;<a href="/app/purchase/updateTranCodeByProd?prodNo=${purchase.purchaseProd.prodNo}&tranCode=2">배송하기</a></td>
				</c:when>
				<c:when test="${purchase.tranCode == '2' }">
				<td align="left">배송중</td>
				</c:when>
				<c:otherwise>
				<td align="left">판매완료</td>
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

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
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
