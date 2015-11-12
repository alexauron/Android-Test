package test.coding.fuse.data.network;

import rx.Observable;
import test.coding.fuse.data.entity.CompanyEntity;

/**
 * RestAPI for retrieving data from the network.
 */
public interface RestAPI {
    String API_PROTOCOL = "https://";

    String API_URL = ".fusion-universal.com";

    String API_SEARCH_COMPANY = "/api/v1/company.json";


    Observable<CompanyEntity> companySearch(final String companyName);
}