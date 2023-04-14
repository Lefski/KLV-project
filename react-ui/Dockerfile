FROM node:latest AS build
WORKDIR /app-client

COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm ci

COPY . .
RUN npm run build

FROM httpd:alpine
WORKDIR /var/www/html
COPY --from=build /app-client .
