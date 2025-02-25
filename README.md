# Franquicias Application

## Prueba Tecnica
API para manejar una lista de franquicias. Una franquicia se
compone por un nombre y un listado de sucursales y, a su vez, una sucursal está
compuesta por un nombre y un listado de productos ofertados en la sucursal. Un producto
se componente de un nombre y una cantidad de stock.


## Diagrama Relacional

![Diagrama Relacional](diagram.png)

## Endpoints

### Franquicia Endpoints
- **GET /franquicias**: Retrieve all franchises.
- **GET /franquicias/{id}**: Retrieve a franchise by its ID.
- **POST /franquicias**: Create a new franchise.
- **DELETE /franquicias/{id}**: Delete a franchise by its ID.

### Sucursal Endpoints
- **POST /franquicias/{id}/sucursales**: Add a new branch to a franchise.

### Producto Endpoints
- **POST /franquicias/{franquiciaId}/sucursales/{sucursalId}/productos**: Add a new product to a branch.
- **DELETE /franquicias/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}**: Delete a product from a branch.
- **PUT /franquicias/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock**: Update the stock of a product in a branch.
- **GET /franquicias/{id}/productos-con-mas-stock**: Retrieve products with the highest stock in a franchise.

## Models

### Franquicia
Represents a franchise with the following attributes:
- `id`: Long
- `name`: String
- `address`: String
- `sucursales`: List<Sucursal>

### Sucursal
Represents a branch with the following attributes:
- `id`: Long
- `name`: String
- `address`: String
- `productos`: List<Producto>

### Producto
Represents a product with the following attributes:
- `id`: Long
- `name`: String
- `price`: Double
- `stock`: int

## Services

### FranquiciaService
Provides methods to manage franchises, branches, and products:
- `findAll()`: List<Franquicia>
- `findById(Long id)`: Franquicia
- `save(Franquicia franquicia)`: Franquicia
- `deleteById(Long id)`: void
- `addSucursal(Long id, Sucursal sucursal)`: Franquicia
- `addProducto(Long franquiciaId, Long sucursalId, Producto producto)`: Sucursal
- `deleteProducto(Long franquiciaId, Long sucursalId, Long productoId)`: void
- `updateStock(Long franquiciaId, Long sucursalId, Long productoId, int stock)`: Producto
- `getProductosConMasStock(Long id)`: List<Producto>

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/j-fernando-c/franquicias-api.git
   ```
2. Navigate to the project directory:
   ```bash
   cd franquicias
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
