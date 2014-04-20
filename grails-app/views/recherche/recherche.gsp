<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bibliotheque.label', default: 'Bibliotheque')}" />
		<title>Resultat de la recherche</title>
	</head>
	<body>
		<a href="#list-bibliotheque" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Nouvelle recherche</a></li>
			</ul>
		</div>
		<div id="list-bibliotheque" class="content scaffold-list" role="main">
			<h1>Résultat de la recherche</h1>
			<g:if test="${flash.message}">
			<div class="errors" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th>
						</th>
						<th>
							Titre
						</th>
						<th>
							Auteurs
						</th>
						<th>
							Type de document
						</th>
						<th>
							Quantité disponible
						</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${livresInstances}" status="i" var="livre">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
							<g:if test="${ livre.qteDispo > 0 }">
								<g:link action="ajouterAuPanier" params='[id: "${ livre.id }",
																	  titreRecherche: "${titreRecherche}",
																	  auteurRecherche: "${auteurRecherche}",
																	  typeDocumentRecherche: "${typeDocumentRecherche}",
																	  qteDispoRecherche: "${qteDispoRecherche}"]' >
									<img src="${resource(dir: 'images', file: 'cart.png')}" alt="Ajouter au panier" />
								</g:link>
							</g:if>
							<g:else>
								<img src="${resource(dir: 'images', file: 'cart_cross.png')}" alt="Article indisponible" />
							</g:else>
						</td>
						<td>
							${livre.titre}
						</td>
						<td>
							<g:each in="${livre.auteurs}" status="j" var="auteur">
								${auteur}<br />
							</g:each>
						</td>
						<td>
							${livre.typeDocument}
						</td>
						<td>
							${livre.qteDispo}
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${livresInstanceTotal}" params='[titreRecherche: "${titreRecherche}",
																	auteurRecherche: "${auteurRecherche}",
																	typeDocumentRecherche: "${typeDocumentRecherche}",
																	qteDispoRecherche: "${qteDispoRecherche}"]' />
			</div>
		</div>
	</body>
</html>
