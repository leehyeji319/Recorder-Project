FROM node:14.16.1-alpine
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH

ADD package.json /app/package.json
RUN npm install
RUN npm install react-scripts@3.4.1 -g

CMD ["npm", "start"]
