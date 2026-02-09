# High Performance Terrakube Registry (Go) 🚀

[![Go Report Card](https://goreportcard.com/badge/github.com/ilkerispir/terrakube-registry)](https://goreportcard.com/report/github.com/ilkerispir/terrakube-registry)
[![License](https://img.shields.io/github/license/ilkerispir/terrakube-registry)](LICENSE.md)
[![Docker Pulls](https://img.shields.io/docker/pulls/ilkerispir/terrakube-registry)](https://hub.docker.com/r/ilkerispir/terrakube-registry)

A lightweight, high-performance, community-supported alternative to the official Java-based **Terrakube Registry** service, written entirely in **Go**.

> **Note:** This project is fully API-compatible with the official Terrakube Registry but offers significantly faster startup times and lower resource consumption. It is recognized as an official community alternative in the [Terrakube Documentation](https://docs.terrakube.io).

## ✨ Why Go?

| Feature | Official Java Registry | Go Registry (This Project) |
| :--- | :--- | :--- |
| **Startup Time** | ~15-30 seconds | **< 1 second** ⚡ |
| **Memory Usage** | 500MB+ (JVM overhead) | **~20MB** 🪶 |
| **Image Size** | Large (JDK dependency) | **Tiny** (Alpine/Distroless build) |
| **Philosophy** | Enterprise Java | **Cloud-Native Go** |

## 📦 Features

*   ✅ **Full Protocol Support:** Implements Terraform [Module Registry Protocol](https://developer.hashicorp.com/terraform/internals/module-registry-protocol) and [Provider Registry Protocol](https://developer.hashicorp.com/terraform/internals/provider-registry-protocol).
*   ✅ **Multi-Cloud Storage:** Seamlessly stores modules and providers on:
    *   **AWS S3** (and MinIO compatible storage)
    *   **Azure Blob Storage**
    *   **Google Cloud Storage (GCP)**
*   ✅ **Private Git Support:** Securely clones private modules using Git over SSH/HTTPS with authentication tokens.
*   ✅ **Drop-in Replacement:** Uses the exact same environment variables as the official Terrakube Registry. Zero configuration changes required for migration.

## 🚀 Getting Started

### Docker Compose

Simply request the new image in your existing Terrakube `docker-compose.yml`:

```yaml
version: '3'
services:
  registry:
    image: ilkerispir/terrakube-registry:latest # <--- Switch to the Go implementation
    environment:
      - STORAGE_TYPE=AWS # or AZURE, GCP
      - AWS_ACCESS_KEY_ID=minio
      - AWS_SECRET_ACCESS_KEY=miniostorage
      - AWS_REGION=us-east-1
      - AWS_BUCKET_NAME=terrakube-registry
      - AWS_ENDPOINT=http://minio:9000
    ports:
      - "8080:8080"
```

### Configuration

All configuration is managed via Environment Variables. See `.env.example` for a complete list.

| Variable | Description | Default |
| :--- | :--- | :--- |
| `STORAGE_TYPE` | Backend storage type (`AWS`, `AZURE`, `GCP`) | `AWS` |
| `PORT` | Service port | `8080` |
| `GIT_TIMEOUT` | Timeout for git clone operations | `120` (seconds) |

## 🛠️ Building from Source

```bash
# Clone the repository
git clone https://github.com/ilkerispir/terrakube-registry.git

# Build the binary
go build -o registry main.go

# Run
./registry
```

## 🤝 Contributing

We welcome contributions of all forms! Whether it's bug reports, feature requests, or pull requests, please feel free to contribute.

## 📄 License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE.md) file for details.

---
_This project is an independent community effort and is not directly affiliated with the core maintenance team of Terrakube, but is fully compatible and endorsed as a community alternative._
