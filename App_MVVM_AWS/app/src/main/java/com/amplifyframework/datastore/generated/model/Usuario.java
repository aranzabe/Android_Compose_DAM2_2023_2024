package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Usuario type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Usuarios", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Usuario implements Model {
  public static final QueryField ID = field("Usuario", "id");
  public static final QueryField NOMBRE = field("Usuario", "nombre");
  public static final QueryField EDAD = field("Usuario", "edad");
  public static final QueryField PARTE = field("Usuario", "parte");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String nombre;
  private final @ModelField(targetType="Int") Integer edad;
  private final @ModelField(targetType="Boolean") Boolean parte;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getNombre() {
      return nombre;
  }
  
  public Integer getEdad() {
      return edad;
  }
  
  public Boolean getParte() {
      return parte;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Usuario(String id, String nombre, Integer edad, Boolean parte) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
    this.parte = parte;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Usuario usuario = (Usuario) obj;
      return ObjectsCompat.equals(getId(), usuario.getId()) &&
              ObjectsCompat.equals(getNombre(), usuario.getNombre()) &&
              ObjectsCompat.equals(getEdad(), usuario.getEdad()) &&
              ObjectsCompat.equals(getParte(), usuario.getParte()) &&
              ObjectsCompat.equals(getCreatedAt(), usuario.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), usuario.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNombre())
      .append(getEdad())
      .append(getParte())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Usuario {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("nombre=" + String.valueOf(getNombre()) + ", ")
      .append("edad=" + String.valueOf(getEdad()) + ", ")
      .append("parte=" + String.valueOf(getParte()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NombreStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Usuario justId(String id) {
    return new Usuario(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      nombre,
      edad,
      parte);
  }
  public interface NombreStep {
    BuildStep nombre(String nombre);
  }
  

  public interface BuildStep {
    Usuario build();
    BuildStep id(String id);
    BuildStep edad(Integer edad);
    BuildStep parte(Boolean parte);
  }
  

  public static class Builder implements NombreStep, BuildStep {
    private String id;
    private String nombre;
    private Integer edad;
    private Boolean parte;
    public Builder() {
      
    }
    
    private Builder(String id, String nombre, Integer edad, Boolean parte) {
      this.id = id;
      this.nombre = nombre;
      this.edad = edad;
      this.parte = parte;
    }
    
    @Override
     public Usuario build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Usuario(
          id,
          nombre,
          edad,
          parte);
    }
    
    @Override
     public BuildStep nombre(String nombre) {
        Objects.requireNonNull(nombre);
        this.nombre = nombre;
        return this;
    }
    
    @Override
     public BuildStep edad(Integer edad) {
        this.edad = edad;
        return this;
    }
    
    @Override
     public BuildStep parte(Boolean parte) {
        this.parte = parte;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String nombre, Integer edad, Boolean parte) {
      super(id, nombre, edad, parte);
      Objects.requireNonNull(nombre);
    }
    
    @Override
     public CopyOfBuilder nombre(String nombre) {
      return (CopyOfBuilder) super.nombre(nombre);
    }
    
    @Override
     public CopyOfBuilder edad(Integer edad) {
      return (CopyOfBuilder) super.edad(edad);
    }
    
    @Override
     public CopyOfBuilder parte(Boolean parte) {
      return (CopyOfBuilder) super.parte(parte);
    }
  }
  

  public static class UsuarioIdentifier extends ModelIdentifier<Usuario> {
    private static final long serialVersionUID = 1L;
    public UsuarioIdentifier(String id) {
      super(id);
    }
  }
  
}
