import * as React from 'react';
import { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from "@mui/material/Button";
import { Stack } from "@mui/material";

const RecipeForm = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [recipes, setRecipes] = useState([]);

    const handleSubmitClick = (e) => {
        e.preventDefault();
        const recipe = {name, price};
        fetch("http://localhost:8080/recipe/add", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(recipe)
        }).then(() => {
            console.log('new added');
        })
    }

    useEffect(() => {
        fetch("http://localhost:8080/recipe/get-all")
            .then((res) => res.json())
            .then(result => setRecipes(result))
    }, [])

    console.log(recipes)

    return (
        <Stack display={'flex'} direction={'column'} alignItems={'center'}>
            <Box
                component="form"
                sx={{
                    '& > :not(style)': {m: 1},
                }}
                noValidate
                autoComplete="off"
            >
                <TextField id="recipe-name" label="Recipe Name" variant="standard" value={name}
                           onChange={e => setName(e.target.value)}/>
                <TextField id="recipe-price" label="Recipe Price" variant="standard" type={'number'} value={price}
                           onChange={e => setPrice(e.target.value)}/>
            </Box>
            <Button color="success" variant="contained" sx={{width: 100, mt: 1}}
                    onClick={handleSubmitClick}>Submit</Button>
        </Stack>
    );
}

export default RecipeForm;