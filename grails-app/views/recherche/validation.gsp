<%@ page import="bibliothequecroustillant.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Validation réservation</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Nouvelle recherche</a></li>
			</ul>
		</div>
		<div class="validationMessage">
			Votre réservation a été validée.<br />
			Votre code de réservation est <b>${ reservationCode }</b><br />
			Vous devez récupérer votre réservation avant <b>${ date }</b>.
			<br />
			<br />
			Résumé de votre commande
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
					<g:each in="${ livres }" status="i" var="livre">
						<tr>
							<td>${ Livre.get( livre.key ).titre }</td>
							<td>
								<g:each in="${ Livre.get( livre.key ).auteurs }" status="j" var="auteur">
									${ auteur }<br />
								</g:each>
							</td>
							<td>${ Livre.get( livre.key ).typeDocument }</td>
							<td>${ livre.value }</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>