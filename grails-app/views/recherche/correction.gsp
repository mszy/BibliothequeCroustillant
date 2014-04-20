<%@ page import="bibliothequecroustillant.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Validation réservation</title>
	</head>
	<body>
		<div class="validationMessage">
			<table>
				<thead>
					<tr>
						<th>Titre</th>
						<th>Auteurs</th>
						<th>Type de document</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${ livresIndisponibles }" status="i" var="livre">
						<tr>
							<td>${Livre.get( livre ).titre}</td>
							<td>
								<g:each in="${Livre.get( livre ).auteurs}" status="j" var="auteur">
									${auteur}<br />
								</g:each>
							</td>
							<td>${Livre.get( livre ).typeDocument}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			Ces livres sont indisponibles.
			Voulez-vous continuer la commande avec les livres disponibles ou l'annuler ?
			<g:form action="corrigerPanier">
				<g:hiddenField name="livresIndisponibles" value="${ livresIndisponibles }"/>
				<fieldset class="buttons">
					<g:actionSubmit name="continuer" class="validate" value="${message(code: 'default.button.continue.label', default: 'Continuer')}" action="corrigerPanier" />
					<g:actionSubmit name="annuler" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Annuler')}" action="annulerReservation" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>