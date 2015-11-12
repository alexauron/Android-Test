package test.coding.fuse.data.network.retrofit;

import retrofit.http.GET;
import rx.Observable;
import test.coding.fuse.data.entity.CompanyEntity;
import test.coding.fuse.data.network.RestAPI;

public interface IRetrofitCompanyService {
        @GET(RestAPI.API_SEARCH_COMPANY)
        Observable<CompanyEntity> searchCompany();
}