# First let's check the status
git status

# Create a function for adding files to git.
add() {
    git add $1
    echo "Committed $1"
}

# We now add the file as in the arguments
for i in $(seq 2 $#);
    do add ${!i};
done

# Commit files based on first argument
git commit -m "$1"

# Push repo to origin
git push origin

# Check status again
git status
