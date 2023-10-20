# Use an official Node.js runtime as the base image
FROM node:20 as build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Install app dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Build the Angular app for production
RUN ng build

# Use a smaller image for serving the app
FROM nginx:alpine

# Copy the built Angular app from the previous stage to the NGINX HTML directory
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Expose the port that NGINX will listen on
EXPOSE 80

# Command to start NGINX
CMD ["nginx", "-g", "daemon off;"]

