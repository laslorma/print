package hello.controller;

import hello.domain.App;
import hello.DAO.AppDao;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Victor on 16-Jan-17.
 */
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppDao appDao;

    private final Logger log = LoggerFactory.getLogger(AppController.class);


    /**
     * POST
     * Create a new app
     * @param app
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<App> create(@RequestBody App app) {

        try {

            // Validando
            if (appDao.findByDomain(app.getDomain())!=null) {

                log.error("App domain taken: " + app.getDomain());

                return new ResponseEntity(HttpStatus.BAD_REQUEST);

            }else{

                // Salvando
                appDao.save(app);

            }
        }
        catch (Exception ex) {

            log.error("Error creating app "+ ex.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<App>(app,HttpStatus.OK);
    }

    /**
     * GET
     * @param appId
     * @return
     */
    @RequestMapping(value = {"/{appId}"},
            method = RequestMethod.GET,
            produces="application/json")
    public ResponseEntity<App>  get(@PathVariable(value = "appId")  int appId)
    {
        App app = null;
        try {
            app = appDao.findOne(appId);
        }
        catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<App>(app,HttpStatus.OK);
    }

    /**
     * DELETE
     * @param appId
     * @return
     */
    @RequestMapping(value ="/{appId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable(value = "appId")  int appId) {
        try {
            App app = appDao.findOne(appId);
            appDao.delete(app);
        }
        catch (Exception ex) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<App>(HttpStatus.OK);
    }

    /**
     * GET
     *
     * Nota: el .+ en el request mapping value es para escapar los puntos del nombre de dominio. Por ejemplo: tes.com.ve
     * se trunca a tes.com si no tiene el .+
     *
     * @param domain
     * @return
     */
    @RequestMapping(value = "/get-by-domain/{domain:.+}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<App> getByDomain(@PathVariable(value = "domain") String domain) {


        App app = null;
        try {
            app = appDao.findByDomain(domain);
        }
        catch (Exception ex) {

            log.error("Error getByDomain "+ex.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<App>(app,HttpStatus.OK);
    }


    /**
     * PUT
     * @param app
     * @return
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<App> updateApp(@RequestBody App app) {

        try {
            // Hace save, update o merge depndiendo el caso, lo detecta automaticamente
             app = appDao.save(app);

            appDao.save(app);
        }
        catch (Exception ex) {
            log.error( "Error updating the app: " + ex.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<App> (app,HttpStatus.OK);

    }



}
