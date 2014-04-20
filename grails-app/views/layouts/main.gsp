<%@ page import="bibliothequecroustillant.Livre" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <%@page import="bibliothequecroustillant.Livre"%>
<html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>
		<div id="panier">
			<g:if test="${ session["panier"] != null }">
				<h2>Votre panier :</h2>
				<table>
					<thead>
						<tr>
							<th>Titre</th>
							<th>Auteurs</th>
							<th>Type de document</th>
							<th>Quantité</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${ session["panier"] }" status="i" var="sessionItem" >
							<tr>
								<td>${ Livre.get( sessionItem.key ).titre }</td>
								<td>
									<g:each in="${ Livre.get( sessionItem.key ).auteurs }" status="j" var="auteur">
										${ auteur }<br />
									</g:each>
								</td>
								<td>${ Livre.get( sessionItem.key ).typeDocument }</td>
								<td>${ sessionItem.value }</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="validerPanier">
					<g:form action="controlerPanier">
						<fieldset class="buttons">
							<g:submitButton name="validerPanier" class="validate" value="${message(code: 'default.button.cart.validate.label', default: 'Valider le panier')}" />
						</fieldset>
					</g:form>
				</div>
				<br />
				<hr />
				<hr />
				<br />
			</g:if>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
