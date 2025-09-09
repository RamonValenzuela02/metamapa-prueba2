package ar.edu.utn.frba.dds.domain.ubicacion;

import java.io.IOException;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioProvincia {
  private static ServicioProvincia instancia;
  private static final String urlApi = "https://nominatim.openstreetmap.org/";
  private Retrofit retrofit;

  private ServicioProvincia() {
    OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(new UserAgentInterceptor())
      .build();

    this.retrofit = new Retrofit.Builder()
      .baseUrl(urlApi)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
  }

  public static ServicioProvincia getInstancia() {
    if (instancia == null) {
      instancia = new ServicioProvincia();
    }
    return instancia;
  }

  public ClaseMoldeProvincia provincia(String latitud, String longitud) {
    provinciaService service = retrofit.create(provinciaService.class);
    try {
      Response<ClaseMoldeProvincia> response = service.getProvincia(latitud, longitud, "json").execute();
      if (response.isSuccessful()) {
        return response.body();
      } else {
        throw new RuntimeException("Error en Api: " + response.message());
      }
    } catch (IOException e) {
      throw new RuntimeException("Error en Api: " + e.getMessage());
    }

  }

}
