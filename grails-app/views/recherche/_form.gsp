<%@page import="bibliothequecroustillant.TypeDocument"%>
 
<div class="fieldcontain">
	<label>
		<span>Titre</span>
	</label>
	<g:field name="titreRecherche" type="search"/>
</div>
<div class="fieldcontain">
	<label>
		<span>Auteur</span>
	</label>
	<g:field name="auteurRecherche" type="search"/>
</div>
<div class="fieldcontain">
	<label>
		<span>Type document</span>
	</label>
	<g:select name="typeDocumentRecherche" from="${TypeDocument.list()}"/>
</div>
<div class="fieldcontain">
	<label>
		<span>Qté disponible</span>
	</label>
	<g:field name="qteDispoRecherche" min="0" type="number"/>
</div>

