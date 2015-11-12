package test.coding.fuse.data.network;

import rx.Observable;
import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.data.network.retrofit.IRetrofitCompanyService;

public class RestApiImpl implements RestAPI {

    @Override
    public Observable<CompanyEntity> companySearch(final String companyName) {
        return ((IRetrofitCompanyService) ApiConnection.createGET(
                RestAPI.API_PROTOCOL
                + companyName
                + RestAPI.API_URL)
                .call(IRetrofitCompanyService.class)).searchCompany();
    }
}