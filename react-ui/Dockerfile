FROM node:alpine AS build
WORKDIR /app-client

COPY package.json .
COPY package-lock.json .
RUN npm install

COPY . .
RUN npm build
RUN npm run

FROM httpd:alpine

COPY --from=build /app-client/build /usr/local/apache2/htdocs/

EXPOSE 80

CMD ["httpd", "-D", "FOREGROUND"]
