package bibliothequecroutillant



import org.junit.*
import grails.test.mixin.*

@TestFor(BibliothequeController)
@Mock(Bibliotheque)
class BibliothequeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bibliotheque/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bibliothequeInstanceList.size() == 0
        assert model.bibliothequeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bibliothequeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bibliothequeInstance != null
        assert view == '/bibliotheque/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bibliotheque/show/1'
        assert controller.flash.message != null
        assert Bibliotheque.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bibliotheque/list'

        populateValidParams(params)
        def bibliotheque = new Bibliotheque(params)

        assert bibliotheque.save() != null

        params.id = bibliotheque.id

        def model = controller.show()

        assert model.bibliothequeInstance == bibliotheque
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bibliotheque/list'

        populateValidParams(params)
        def bibliotheque = new Bibliotheque(params)

        assert bibliotheque.save() != null

        params.id = bibliotheque.id

        def model = controller.edit()

        assert model.bibliothequeInstance == bibliotheque
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bibliotheque/list'

        response.reset()

        populateValidParams(params)
        def bibliotheque = new Bibliotheque(params)

        assert bibliotheque.save() != null

        // test invalid parameters in update
        params.id = bibliotheque.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bibliotheque/edit"
        assert model.bibliothequeInstance != null

        bibliotheque.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bibliotheque/show/$bibliotheque.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bibliotheque.clearErrors()

        populateValidParams(params)
        params.id = bibliotheque.id
        params.version = -1
        controller.update()

        assert view == "/bibliotheque/edit"
        assert model.bibliothequeInstance != null
        assert model.bibliothequeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bibliotheque/list'

        response.reset()

        populateValidParams(params)
        def bibliotheque = new Bibliotheque(params)

        assert bibliotheque.save() != null
        assert Bibliotheque.count() == 1

        params.id = bibliotheque.id

        controller.delete()

        assert Bibliotheque.count() == 0
        assert Bibliotheque.get(bibliotheque.id) == null
        assert response.redirectedUrl == '/bibliotheque/list'
    }
}
