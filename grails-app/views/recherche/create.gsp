<%@ page import="bibliothequecroustillant.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title>Rechercher</title>
	</head>
	<body>
		<a href="#create-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="create-livre" class="content scaffold-create" role="main">
			<h1>Recherche</h1>
			<g:if test="${flash.message}">
			<div class="errors" role="status">${flash.message}</div>
			</g:if>
			<g:form action="recherche" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="recherche" class="search" value="${message(code: 'default.button.search.label', default: 'Rechercher')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
