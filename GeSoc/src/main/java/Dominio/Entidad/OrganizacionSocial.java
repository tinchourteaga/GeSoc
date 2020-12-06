package Dominio.Entidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OrganizacionSocial")
public class OrganizacionSocial extends TipoEntidadJuridica{

}
