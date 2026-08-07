// --- Lógica para el Modal de Eliminar ---
let urlAEliminar = '';

function abrirModalEliminar(url) {
    urlAEliminar = url;
    document.getElementById('modalEliminar').classList.add('active');
}

function cerrarModalEliminar() {
    document.getElementById('modalEliminar').classList.remove('active');
    urlAEliminar = '';
}

function confirmarEliminacion() {
    if (urlAEliminar) {
        window.location.href = urlAEliminar; // Redirige a la ruta de eliminación
    }
}

// --- Lógica para el Modal de Cerrar Sesión ---
function abrirModalLogout() {
    document.getElementById('modalLogout').classList.add('active');
}

function cerrarModalLogout() {
    document.getElementById('modalLogout').classList.remove('active');
}

function confirmarLogout() {
    document.getElementById('logoutForm').submit(); // Envía el formulario de Spring Security
}

// Cerrar modales al hacer clic fuera del contenido
window.addEventListener('click', function (event) {
    const modalEliminar = document.getElementById('modalEliminar');
    const modalLogout = document.getElementById('modalLogout');

    // Si el clic fue exactamente en el overlay
    if (event.target === modalEliminar) {
        cerrarModalEliminar();
    }
    if (event.target === modalLogout) {
        cerrarModalLogout();
    }
});

// Cerrar modales al presionar la tecla ESC
window.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
        const modalEliminar = document.getElementById('modalEliminar');
        const modalLogout = document.getElementById('modalLogout');
        
        if (modalEliminar && modalEliminar.classList.contains('active')) {
            cerrarModalEliminar();
        }
        if (modalLogout && modalLogout.classList.contains('active')) {
            cerrarModalLogout();
        }
    }
});