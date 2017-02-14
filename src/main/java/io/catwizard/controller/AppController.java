package io.catwizard.controller;

import io.catwizard.domain.App;
import io.catwizard.DAO.AppDao;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.catwizard.service.LicenseService;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Victor on 16-Jan-17.
 */
@Configuration
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppDao appDao;
    @Value("${key.prop}")
    private String key;

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

            //
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

    @RequestMapping(value = {"/license/{domain:.+}"},
            method = RequestMethod.GET,
            produces="application/json")
    public ResponseEntity<String> generateLicense(@PathVariable(value = "domain") String domain)
    {
        String result = null;
        try {

            LicenseService lic = new LicenseService();
            lic.setKey(key);
            result = lic.generateLicence(domain);

        } catch (NoSuchAlgorithmException ex) {
            log.error( "Error generating license to domain ("+domain+"): " + ex.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String> (result,HttpStatus.OK);
    }

    @RequestMapping(value = {"/license-verify"},
            method = RequestMethod.GET,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> verifyLicense(@RequestHeader(value = "domain", required = true) String domain,
                                  @RequestHeader(value = "key", required = true) String license)
    {
        Boolean result = null;
        try {

            LicenseService lic = new LicenseService();
            lic.setKey(key);
            lic.setAppDao(appDao);
            result = lic.verifyLicense(domain,license);

        } catch (Exception ex) {
            log.error( "Error checking license ("+license+") of domain ("+domain+"): " + ex.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Boolean> (result,(result == true ? HttpStatus.OK: HttpStatus.FORBIDDEN));
    }



}
