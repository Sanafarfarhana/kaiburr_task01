# Task API Documentation

## Introduction

This document describes the REST API for managing and executing tasks that represent shell commands.

## API Endpoints

1. Create a new task
- Method: POST
- URL: /api/tasks
- Request Body: 
  {
    "name": "string",
    "owner": "string",
    "command": "string",
    "taskExecutions": [] (optional)
  }
- Response: 
  Status: 200 OK
  Body: Created task with generated id

2. Get all tasks
- Method: GET
- URL: /api/tasks
- Response: 
  Status: 200 OK
  Body: Array of all tasks

3. Get a specific task
- Method: GET
- URL: /api/tasks/{id}
- Response: 
  Status: 200 OK if found, 404 Not Found if not found
  Body: The requested task

4. Delete a task
- Method: DELETE
- URL: /api/tasks/{id}
- Response: 
  Status: 204 No Content

5. Execute a task
- Method: POST
- URL: /api/tasks/{id}/execute
- Response: 
  Status: 200 OK if found, 404 Not Found if not found
  Body: The updated task with new execution record

## Data Models

Task:
{
  "id": "string",
  "name": "string",
  "owner": "string",
  "command": "string",
  "taskExecutions": [
    {
      "startTime": "date-time",
      "endTime": "date-time",
      "output": "string"
    }
  ]
}

TaskExecution:
{
  "startTime": "date-time",
  "endTime": "date-time",
  "output": "string"
}

Example Requests

Create Task:
POST /api/tasks
{
  "name": "List Files",
  "owner": "Jane Doe",
  "command": "ls -la"
}

Execute Task:
POST /api/tasks/123/execute

Response:
{
  "id": "123",
  "name": "List Files",
  "owner": "Jane Doe",
  "command": "ls -la",
  "taskExecutions": [
    {
      "startTime": "2023-06-15T10:30:00Z",
      "endTime": "2023-06-15T10:30:01Z",
      "output": "Simulated output for command: ls -la"
    }
  ]
}