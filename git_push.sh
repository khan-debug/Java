#!/bin/bash

# Usage: ./git_push.sh "commit message"

# Check if a commit message was provided
if [ -z "$1" ]; then
  echo "Error: No commit message provided."
  echo "Usage: $0 \"commit message\""
  exit 1
fi

# Check if the current directory is a Git repository
if [ ! -d .git ]; then
  echo "This is not a Git repository. Initializing repository."
  git init
fi

# Check if the remote origin is set
if ! git remote get-url origin &>/dev/null; then
  read -p "Enter the GitHub repository URL (e.g., https://github.com/username/repo.git): " REPO_URL
  git remote add origin $REPO_URL
else
  REPO_URL=$(git remote get-url origin)
fi

# Add all files to staging
git add .

# Show the status
git status

# Commit with the provided message
git commit -m "$1"

# Rename the branch to main
git branch -M main

# Set the URL for the remote origin with the personal access token from the environment variable
git remote set-url origin https://$GITHUB_PAT@${REPO_URL#https://}

# Push to the main branch
git push -u origin main

# Remove this script from the repository
SCRIPT_NAME=$(basename "$0")
git rm --cached "$SCRIPT_NAME"

# Commit the removal of the script
git commit -m "Remove $SCRIPT_NAME from the repository"

# Push the commit that removes the script
git push

