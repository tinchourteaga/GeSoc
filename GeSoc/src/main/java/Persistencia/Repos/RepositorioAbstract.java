package Persistencia.Repos;
import javax.persistence.EntityManager;

public abstract class RepositorioAbstract<T> implements IRepositorio<T> {
        private EntityManager em;
        public RepositorioAbstract(EntityManager em){
            this.em = em;
        }
        @Override
        public void agregar(T entity) {
            em.persist(entity);
        }
        @Override
        public void borrar(String id) {
            T entity = obtener(id);
            em.remove(entity);
        }
        @Override
        public void modificar(T entity) {
            em.merge(entity);
        }

        @Override
        public T obtener(String id) {
            return em.find(obtenerClaseDeEntidad(), id);
        }

        @Override
        public boolean existe(String id) {
            return em.contains(obtener(id));
        }

        // https://stackoverflow.com/questions/40635734/using-generics-and-jpa-entitymanager-methods
        public abstract Class<T> obtenerClaseDeEntidad();
}
