/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpcontrollers;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author alicemts
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(httpcontrollers.ActivatedUsersResource.class);
        resources.add(httpcontrollers.AuctionResource.class);
        resources.add(httpcontrollers.BidResource.class);
        resources.add(httpcontrollers.BidersResource.class);
        resources.add(httpcontrollers.CategoryResource.class);
        resources.add(httpcontrollers.CountryResource.class);
        resources.add(httpcontrollers.ItemHasCategoryResource.class);
        resources.add(httpcontrollers.ItemHasCountryResource.class);
        resources.add(httpcontrollers.ItemResource.class);
        resources.add(httpcontrollers.MessageResource.class);
        resources.add(httpcontrollers.PendingUsersResource.class);
        resources.add(httpcontrollers.RejectedUsersResource.class);
        resources.add(httpcontrollers.SellersResource.class);
        resources.add(httpcontrollers.UserHasCountryResource.class);
        resources.add(httpcontrollers.UserHasHistoryResource.class);
        resources.add(httpcontrollers.UserResource.class);
    }
    
}
