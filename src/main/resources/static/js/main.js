// Función para interceptar y confirmar la eliminación
function confirmarEliminacion(event) {
    if (!confirm('¿Estás seguro de eliminar este producto? Esta acción no se puede deshacer.')) {
        event.preventDefault(); // Detiene la navegación hacia la ruta de borrado
    }
}