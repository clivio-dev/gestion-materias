#!/bin/bash

# --- CONFIGURACIÓN ---
APP_NAME="gestor-materias"
VPS_ALIAS="javastral1"
VPS_DIR="~/app-deployment"
# ---------------------

echo "🚀 Iniciando proceso de despliegue..."

# 1. Limpiar y Construir el JAR localmente
echo "📦 Compilando el proyecto con Maven..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Error en la compilación. Abortando."
    exit 1
fi

# 2. Construir la imagen de Docker localmente
echo "🐳 Construyendo imagen Docker..."
docker build -t $APP_NAME -f Dockerfile.prod .

# 3. Guardar la imagen en un archivo comprimido
echo "💾 Exportando imagen a .tar..."
docker save $APP_NAME | gzip > $APP_NAME.tar.gz

# 4. Crear carpeta en la VPS y subir archivos necesarios
echo "📤 Subiendo archivos a la VPS..."
ssh $VPS_ALIAS "mkdir -p $VPS_DIR"
scp $APP_NAME.tar.gz docker-compose.yml $VPS_ALIAS:$VPS_DIR/

# 5. Cargar imagen en la VPS y levantar contenedores
echo "🏗️ Levantando contenedores en la VPS..."
ssh $VPS_ALIAS "cd $VPS_DIR && \
    gunzip -c $APP_NAME.tar.gz | docker load && \
    docker compose up -d"

# 6. Limpieza local
echo "🧹 Limpiando archivos temporales locales..."
rm $APP_NAME.tar.gz

echo "✅ ¡Despliegue completado con éxito!"
