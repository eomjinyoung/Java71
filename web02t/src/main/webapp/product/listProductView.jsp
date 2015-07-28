<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetList(currentPage){
	document.detailForm.currentPage.value = currentPage;
	document.detailForm.submit();
}
function fncGetSortList(sort){
	document.detailForm.sort.value = sort;
	this.fncGetList(1);
}

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
<c:if test="${search.menu == 'manage' }">
	<form name="detailForm" action="/app/product/getProductList?menu=manage" method="post">
</c:if>
<c:if test="${search.menu == 'search' }">
	<form name="detailForm" action="/app/product/getProductList?menu=search" method="post">
</c:if>
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					${search.menu == "manage" ? "��ǰ����" : "��ǰ ��� ��ȸ" }
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
		
				<option value="0"${search.searchCondition.equals("0") ? "selected" : ""}>��ǰ��ȣ</option>
				<option value="1"${search.searchCondition.equals("1") ? "selected" : ""}>��ǰ��</option>
				<option value="2"${search.searchCondition.equals("2") ? "selected" : ""}>��ǰ����</option>
		
			</select>			
				<input type="text" name="searchKeyword" value="${search.searchKeyword != null ? search.searchKeyword : ''}"
					 onkeypress="javascript:if(event.keyCode==13) {fncGetList('1');}" class="ct_input_g"  style="width:200px; height:19px" />			
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1');">�˻�</a>
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
		<td colspan="8" >��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
		<td align="right">
			<a href="javascript:fncGetSortList('1');">���� ���ݼ�</a>
		&nbsp;&nbsp;
			<a href="javascript:fncGetSortList('2');">���� ���ݼ�</a>
		</td>
		<input type="hidden" name="sort" value="${search.sort}">
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:forEach var="product" items="${list }">
	
	<tr class="ct_list_pop">
		<td align="center">${product.prodNo}</td>
		<td></td>
		
		<c:choose>
			<c:when test="${product.proTranCode == '0' && search.menu == 'manage'}">
				<td align="left"><a href="/app/product/updateProductView?prodNo=${product.prodNo}&menu=manage">${product.prodName}</a></td>			
			</c:when>
			<c:when test="${product.proTranCode == '0' && search.menu == 'search'}">
				<td align="left"><a href="/app/product/getProduct?prodNo=${product.prodNo}&menu=search">${product.prodName}</a></td>
			</c:when>
			<c:otherwise>
				<td align="left">${product.prodName}</td>			
			</c:otherwise>
		</c:choose>
		<td></td>
		<td align="left">${product.price}</td>
		<td></td>
		<td align="left">${product.regDate}</td>
		<td></td>
		
		<c:if test="${product.proTranCode == '0' }">
			<td align="left">�Ǹ���</td>
		</c:if>
		<c:if test="${product.proTranCode != '0' && search.menu == 'manage'}">
			<c:choose>
				<c:when test="${product.proTranCode == '1' }">
					<td align="left">���ſϷ�&nbsp;<a href="/app/purchase/updateTranCodeByProd?prodNo=${product.prodNo }&tranCode=2">����ϱ�</a></td>
				</c:when>
				<c:when test="${product.proTranCode == '2' }">
					<td align="left">�����</td>
				</c:when>
				<c:otherwise>
					<td align="left">�ǸſϷ�</td>
				</c:otherwise>
			</c:choose>
		</c:if>		
		<c:if test="${product.proTranCode != '0' && search.menu == 'search'}">
			<td align="left">��� ����</td>
		</c:if>		
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
		<input type="hidden" name="currentPage" value=""/>
		
		<jsp:include page="../common/pageNavigator.jsp"/>	
    	
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>
