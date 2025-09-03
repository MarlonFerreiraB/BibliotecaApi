package io.github.marlon.bibliotecaapi.bibliotecaapi.repository.specs;

import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<BookModel> titleEqual(String title){
        return ((root, query, cb)
                -> cb.equal(root.get("title"),title) );
    }

    public static  Specification<BookModel> generoEqual(GeneroEnum generoEnum){
        return (root, query, cb)
                -> cb.equal(root.get("generoEnum"),generoEnum);
    }

    public static Specification<BookModel> dataDepublicacaoEqual(Integer anoPublicacao){
        return (root, query, cb)
                -> cb.equal(cb.function("year",Integer.class,root.get("publicationYear")),anoPublicacao);
    }

    public static Specification<BookModel> AutorjoinLivro(String name){
        return (root, query, cb)
                -> cb.like(cb.upper(root.get("author").get("name")),"%" + name + "%");
    }

    /*public static Specification<BookModel> AutorjoinLivro(String name){
        return (root, query, cb) -> {
            // Cria um JOIN explícito com a entidade "author"
            Join<Object, Object> joinAuthor = root.join("author", JoinType.LEFT);
            // Usa a Join para acessar a propriedade "name"
            return cb.like(cb.upper(joinAuthor.get("name")), "%" + name + "%");
        };
    }*/
}
