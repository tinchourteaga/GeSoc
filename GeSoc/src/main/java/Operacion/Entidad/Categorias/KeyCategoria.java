package Operacion.Entidad.Categorias;

import Operacion.Entidad.Sector;

public class KeyCategoria {
        TipoCategoria tipoCategoria;
        Sector sector;

        public KeyCategoria(TipoCategoria tipoCategoria,
                            Sector sector){
            this.tipoCategoria=tipoCategoria;
            this.sector=sector;
        }

}

