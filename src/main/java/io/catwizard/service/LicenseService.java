package io.catwizard.service;

import io.catwizard.DAO.AppDao;
import io.catwizard.domain.App;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Victor on 07-Feb-17.
 */
public class LicenseService {

    private AppDao appDao;
    private String key;

    public String generateLicence(String domain) throws NoSuchAlgorithmException
    {
        StringBuffer sb = null;
        String word = null;

        if (!domain.isEmpty())
        {
            word = key + domain;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(word.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        }

        return sb.toString();
    }

    public Boolean verifyLicense(String domain, String licence)
    {
        Boolean result = false;

        if (!domain.isEmpty() && !licence.isEmpty())
        {
            try
            {
                App model = appDao.findByDomain(domain);

                if (model != null)
                {
                    if (model.getLicenseHashkey().equals(licence))
                    {
                        result = true;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public AppDao getAppDao() {
        return appDao;
    }

    public void setAppDao(AppDao appDao) {
        this.appDao = appDao;
    }

}
