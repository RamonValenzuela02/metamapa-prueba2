package ar.edu.utn.frba.dds.domain.ubicacion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface provinciaService {
  @GET
  Call<ClaseMoldeProvincia> getProvincia(
    @Query("lat") String latitud,
    @Query("lon") String longitud,
    @Query("format") String format);

}
