FROM node:18-alpine3.17
WORKDIR /react-ui
ENV PATH /react-ui/node_modules/.bin:$PATH
COPY package.json .
COPY package-lock.json .
RUN npm install # -g npm@8.9.0
COPY . .
EXPOSE 3001
CMD ["npm", "run", "dev"]