package com.nequi.franquicias.controller;

import com.nequi.franquicias.models.Franquicia;
import com.nequi.franquicias.models.Producto;
import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.service.FranquiciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @Operation(
            summary = "Obtener todas las franquicias",
            description = "Retorna una lista de todas las franquicias registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<Franquicia>> findAll() {
        List<Franquicia> franquicias = franquiciaService.findAll();
        return new ResponseEntity<>(franquicias, HttpStatus.OK);
    }


    @Operation(
            summary = "Obtener una franquicia por ID",
            description = "Retorna una franquicia específica basada en su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Franquicia encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class))),
                    @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Franquicia> findById(@PathVariable @Min(1) Long id) {
        Franquicia franquicia = franquiciaService.findById(id);
        if (franquicia != null) {
            return new ResponseEntity<>(franquicia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Crear una nueva franquicia",
            description = "Registra una nueva franquicia en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Franquicia> save(@RequestBody @Valid Franquicia franquicia) {
        Franquicia savedFranquicia = franquiciaService.save(franquicia);
        return new ResponseEntity<>(savedFranquicia, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar una franquicia por ID",
            description = "Elimina una franquicia específica basada en su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Franquicia eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(1) Long id) {
        franquiciaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(
            summary = "Agregar una nueva sucursal a una franquicia",
            description = "Registra una nueva sucursal a una franquicia.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucursal agregada exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @PostMapping("/{id}/sucursales")
    public ResponseEntity<Franquicia> addSucursal(@PathVariable @Min(1) Long id, @RequestBody @Valid Sucursal sucursal) {
        Franquicia updatedFranquicia = franquiciaService.addSucursal(id, sucursal);
        if (updatedFranquicia != null) {
            return new ResponseEntity<>(updatedFranquicia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Agregar un nuevo producto a una Sucursal",
            description = "Registra un Producto a una Sucusal.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto Registrado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Sucursal> addProducto(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @RequestBody @Valid Producto producto) {
        Sucursal updatedSucursal = franquiciaService.addProducto(franquiciaId, sucursalId, producto);
        if (updatedSucursal != null) {
            return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Elimina un producto de una Sucursal",
            description = "Borra un Producto de una Sucursal.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto Eliminado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Void> deleteProducto(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @PathVariable @Min(1) Long productoId) {
        franquiciaService.deleteProducto(franquiciaId, sucursalId, productoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Actualiza el Stock de un producto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Stock actualizado Correctamente.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<Producto> updateStock(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @PathVariable @Min(1) Long productoId, @RequestParam @Min(0) int stock) {
        Producto updatedProducto = franquiciaService.updateStock(franquiciaId, sucursalId, productoId, stock);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Consulta el Producto con más stock de una Sucursal",
            description = "Registra una nueva sucursal a una franquicia.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Consulta Exitosa",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class)))
            }
    )
    @GetMapping("/{id}/productos-con-mas-stock")
    public ResponseEntity<List<Producto>> getProductosConMasStock(@PathVariable @Min(1) Long id) {
        List<Producto> productos = franquiciaService.getProductosConMasStock(id);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /*seccion de puntos extra*/

    @Operation(
            summary = "Actualizar el nombre de una franquicia",
            description = "Actualiza el nombre de una franquicia específica basada en su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Nombre de la franquicia actualizado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Franquicia.class))),
                    @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
            }
    )
    @PutMapping("/{id}/nombre")
    public ResponseEntity<Franquicia> updateFranquiciaNombre(@PathVariable @Min(1) Long id, @RequestParam String nombre) {
        Franquicia updatedFranquicia = franquiciaService.updateFranquiciaNombre(id, nombre);
        if (updatedFranquicia != null) {
            return new ResponseEntity<>(updatedFranquicia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Actualizar el nombre de una sucursal",
            description = "Actualiza el nombre de una sucursal específica basada en su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Nombre de la sucursal actualizado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class))),
                    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
            }
    )
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/nombre")
    public ResponseEntity<Sucursal> updateSucursalNombre(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @RequestParam String nombre) {
        Sucursal updatedSucursal = franquiciaService.updateSucursalNombre(franquiciaId, sucursalId, nombre);
        if (updatedSucursal != null) {
            return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Actualizar el nombre de un producto",
            description = "Actualiza el nombre de un producto específico basado en su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Nombre del producto actualizado exitosamente",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/nombre")
    public ResponseEntity<Producto> updateProductoNombre(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @PathVariable @Min(1) Long productoId, @RequestParam String nombre) {
        Producto updatedProducto = franquiciaService.updateProductoNombre(franquiciaId, sucursalId, productoId, nombre);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
