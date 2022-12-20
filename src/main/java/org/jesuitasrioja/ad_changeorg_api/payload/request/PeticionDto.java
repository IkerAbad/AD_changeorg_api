package org.jesuitasrioja.ad_changeorg_api.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionDto {

    private Long id;

    private String titulo;

    private String destinatario;

    private String descripcion;

    private Long categoria_id;



}
