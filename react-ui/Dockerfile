FROM node:alpine AS build
WORKDIR /app-client

COPY package.json package-lock.json ./
RUN npm ci

COPY . ./
RUN npm run build

FROM httpd:alpine
WORKDIR /var/www/html
COPY --from=build /app-client .
