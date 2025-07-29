📍 Nav Drawer - App Android con Google Maps y Marcadores Interactivos
📖 Descripción
Este proyecto es una aplicación Android que integra Google Maps para mostrar distintas estaciones en un mapa, cada una con un marcador personalizado, información breve y un diálogo interactivo con más detalles.

El usuario puede:
Visualizar su ubicación actual en el mapa (opcional).
Consultar la información de cada estación pulsando sobre el marcador.
Introducir una contraseña para desbloquear la siguiente estación.
Alternar la visualización de su ubicación mediante un Switch.

✨ Características principales
Integración de Google Maps API con SupportMapFragment.
Marcadores personalizados con iconos propios.
Ventanas de información (InfoWindow) y apertura de DialogFragment al pulsarlas.
Control de permisos de ubicación en tiempo de ejecución.
Estilo de mapa personalizado mediante recurso raw/map_style.json.
Uso de FusedLocationProviderClient para obtener la última ubicación conocida.
Interfaz adaptable usando ViewBinding (ActivityMainBinding).

🛠️ Tecnologías utilizadas
Java (Android)
Google Maps API
AndroidX
ViewBinding
Material Design Components
FusedLocationProviderClient (Google Play Services Location)

🚀 Instalación y ejecución
Clonar el repositorio:
git clone https://github.com/AdriBelSer/nav_drawer.git
Abrir en Android Studio.

Configurar Google Maps API Key:
Solicita una API Key en Google Cloud Console.
Añádela en google_maps_api.xml dentro de res/values/.
Ejecutar en un dispositivo o emulador con Google Play Services.

📷 Capturas de pantalla
<img width="1080" height="2400" alt="Screenshot_20250729_123236" src="https://github.com/user-attachments/assets/423078ae-7a85-469e-8bfb-b237d942acda" />
<img width="1080" height="2400" alt="Screenshot_20250729_123152" src="https://github.com/user-attachments/assets/2ba6cae2-519f-4a80-865c-9f4119ed2bfc" />
<img width="1080" height="2400" alt="Screenshot_20250729_123044" src="https://github.com/user-attachments/assets/3cbc0381-7af9-4d90-ac19-a95aa7c17fb9" />
