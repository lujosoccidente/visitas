
let map;
let marker;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: -34.397, lng: 150.644 },
        zoom: 18
    });

    marker = new google.maps.Marker({
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP
    });

    google.maps.event.addListener(marker, 'dragend', function () {
        updateLocation(marker.getPosition());
    });

    // Inicializa la ubicación con las coordenadas de la visita (si están disponibles)
    const latitud = parseFloat(document.getElementById('latitud').value);
    const longitud = parseFloat(document.getElementById('longitud').value);

    if (!isNaN(latitud) && !isNaN(longitud)) {
		console.log("actualizando ubicacion")
        const initialLocation = new google.maps.LatLng(latitud, longitud);
        marker.setPosition(initialLocation);
        map.setCenter(initialLocation);
        //updateLocation(initialLocation);
    }
}

initMap()

function obtenerUbicacion(e) {
	e.preventDefault();
  // Solicitamos el permiso al usuario
  navigator.geolocation.getCurrentPosition(
    function(posicion) {
      // Obtenemos la ubicación
      let latitud = posicion.coords.latitude;
      let longitud = posicion.coords.longitude;

      // Determinamos el lugar
      determinarLugar(latitud, longitud);
    },
    function(error) {
      // Se produjo un error
      console.log(error);
    }
  );
}

// Determinamos el lugar
async function determinarLugar(latitud, longitud) {
  // Llamamos a la API de Google Maps
  const geocoder = new google.maps.Geocoder();
  geocoder.geocode({
    location: {lat: latitud, lng: longitud}
  }, async function(results, status) {
    // Si la solicitud fue exitosa
    if (status === google.maps.GeocoderStatus.OK) {
      // Imprimimos la dirección
      let direccion = results[0].formatted_address;
      const ubicacionParrafo = document.getElementById("ubicacion");
      ubicacionParrafo.innerHTML = `${direccion}`;

      // Initialize and add the map
      let map;
      const position = { lat: latitud, lng: longitud };

      // Request needed libraries.
      //@ts-ignore
      const { Map } = await google.maps.importLibrary("maps");
      const { Marker } = await google.maps.importLibrary("marker");

      // The map, centered at Uluru
      map = new Map(document.getElementById("map"), {
        zoom: 18,
        center: position,
        mapId: "DEMO_MAP_ID",
      });

      // Add a marker to the map
      const marker = new Marker({
        position: position,
        map: map,
      });
      
      document.getElementById("latitud").value = latitud;
      document.getElementById("longitud").value = longitud;
      console.log(direccion)
      document.getElementById("ubicacion-visita").value = direccion;
    } else {
      // Se produjo un error
      console.log(status);
    }
  });
}