package br.com.wesleygalindo.aulalistawebservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wesley on 15/05/18.
 */

public interface ITeatroWebService {

    @GET("teatros")
    Call<List<Teatro>> getTeatros();
}
