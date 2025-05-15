# 1: Build the React app
FROM node:22 
# AS builder
WORKDIR /fin-app
COPY . .
# ENV REACT_APP_BACKEND_URL=http://localhost:8080
RUN npm install
RUN npm run build
EXPOSE 3000
ENTRYPOINT ["npm", "start"]

# 2: Serve the React app with Nginx
# FROM nginx:alpine
# COPY --from=builder /fin-app/build /usr/share/nginx/html
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]